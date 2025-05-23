package br.com.artvision.controllers;

import br.com.artvision.dao.FuncionarioDAO;
import br.com.artvision.models.Funcionario;
import br.com.artvision.services.CargoService;
import br.com.artvision.services.DepartamentoService;
import br.com.artvision.services.SetorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/sistema/funcionario")
public class FuncionarioController extends HttpServlet {

    private FuncionarioDAO funcionarioDAO;
    private CargoService cargoService;
    private SetorService setorService;
    private DepartamentoService departamentoService;

    @Override
    public void init() {
        funcionarioDAO = new FuncionarioDAO();
        cargoService = new CargoService();
        setorService = new SetorService();
        departamentoService = new DepartamentoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                listarFuncionarios(request, response);
            } else {
                switch (action) {
                    case "listar":
                        listarFuncionarios(request, response);
                        break;
                    case "editar":
                        mostrarFormularioEdicao(request, response);
                        break;
                    case "excluir":
                        excluirFuncionario(request, response);
                        break;
                    case "novo":
                        mostrarFormularioCadastro(request, response);
                        break;
                    default:
                        listarFuncionarios(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("cadastrar".equals(action)) {
            cadastrarFuncionario(request, response);
        } else if ("atualizar".equals(action)) {
            atualizarFuncionario(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/sistema/funcionario?action=listar");
        }
    }

    private void listarFuncionarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("funcionarios", funcionarioDAO.listarFuncionarios());
        request.getRequestDispatcher("/sistema/funcionario.jsp").forward(request, response);
    }

    private void mostrarFormularioCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cargos", cargoService.listarCargos());
        request.setAttribute("setores", setorService.listarSetor());
        request.setAttribute("departamentos", departamentoService.listarDepartamentos());
        request.getRequestDispatcher("/sistema/add_funcionario.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idFunc = Integer.parseInt(request.getParameter("id_func"));
        Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorId(idFunc);

        request.setAttribute("cargos", cargoService.listarCargos());
        request.setAttribute("setores", setorService.listarSetor());
        request.setAttribute("departamentos", departamentoService.listarDepartamentos());

        request.setAttribute("funcionario", funcionario);
        request.getRequestDispatcher("/sistema/add_funcionario.jsp").forward(request, response);
    }

    private void cadastrarFuncionario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Funcionario funcionario = criarFuncionarioAPartirDoRequest(request);
        funcionarioDAO.cadastrarFuncionario(funcionario);
        response.sendRedirect(request.getContextPath() + "/sistema/funcionario?action=listar");
    }

    private void atualizarFuncionario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Funcionario funcionario = criarFuncionarioAPartirDoRequest(request);
        funcionario.setIdFunc(Integer.parseInt(request.getParameter("id_func")));
        funcionarioDAO.atualizarFuncionario(funcionario);
        response.sendRedirect(request.getContextPath() + "/sistema/funcionario?action=listar");
    }

    private void excluirFuncionario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idFunc = Integer.parseInt(request.getParameter("id_func"));
        funcionarioDAO.excluirFuncionario(idFunc);
        response.sendRedirect(request.getContextPath() + "/sistema/funcionario?action=listar");
    }

    private Funcionario criarFuncionarioAPartirDoRequest(HttpServletRequest request) {
        Funcionario funcionario = new Funcionario();

        funcionario.setNomeFunc(request.getParameter("nome_func"));
        funcionario.setCpfFunc(request.getParameter("cpf_func"));
        funcionario.setEmailFunc(request.getParameter("email_func"));
        funcionario.setTelefoneFunc(request.getParameter("telefone_func"));
        funcionario.setMatriculaFunc(request.getParameter("matricula_func"));
        funcionario.setIdCargo(parseIntOrZero(request.getParameter("id_cargo")));
        funcionario.setIdSetor(parseIntOrZero(request.getParameter("id_setor")));
        funcionario.setIdDepto(parseIntOrZero(request.getParameter("id_depto")));

        return funcionario;
    }

    private int parseIntOrZero(String param) {
        if (param == null || param.trim().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(param);
    }
}
