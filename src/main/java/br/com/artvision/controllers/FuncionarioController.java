package br.com.artvision.controllers;

import br.com.artvision.dao.FuncionarioDAO;
import br.com.artvision.models.Funcionario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/funcionario")
public class FuncionarioController extends HttpServlet {

    private FuncionarioDAO funcionarioDAO;

    @Override
    public void init() {
        funcionarioDAO = new FuncionarioDAO();
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
        String idFuncStr = request.getParameter("idFunc");

        if (idFuncStr == null || idFuncStr.isEmpty()) {
            cadastrarFuncionario(request, response);
        } else {
            atualizarFuncionario(request, response);
        }
    }

    // LISTAR FUNCIONÁRIOS
    private void listarFuncionarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listaFuncionarios", funcionarioDAO.listarFuncionarios());
        request.getRequestDispatcher("funcionario-lista.jsp").forward(request, response);
    }

    // MOSTRAR FORMULÁRIO DE EDIÇÃO
    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idFunc = Integer.parseInt(request.getParameter("idFunc"));
        Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorId(idFunc);
        request.setAttribute("funcionario", funcionario);
        request.getRequestDispatcher("funcionario-form.jsp").forward(request, response);
    }

    // CADASTRAR FUNCIONÁRIO
    private void cadastrarFuncionario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Funcionario funcionario = criarFuncionarioAPartirDoRequest(request);
        funcionarioDAO.cadastrarFuncionario(funcionario);
        response.sendRedirect("funcionario?action=listar");
    }

    // ATUALIZAR FUNCIONÁRIO
    private void atualizarFuncionario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Funcionario funcionario = criarFuncionarioAPartirDoRequest(request);
        funcionario.setIdFunc(Integer.parseInt(request.getParameter("idFunc")));
        funcionarioDAO.atualizarFuncionario(funcionario);
        response.sendRedirect("funcionario?action=listar");
    }

    // EXCLUIR FUNCIONÁRIO
    private void excluirFuncionario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idFunc = Integer.parseInt(request.getParameter("idFunc"));
        funcionarioDAO.excluirFuncionario(idFunc);
        response.sendRedirect("funcionario?action=listar");
    }

    // MÉTODO AUXILIAR PARA CRIAR FUNCIONÁRIO A PARTIR DO REQUEST
    private Funcionario criarFuncionarioAPartirDoRequest(HttpServletRequest request) {
        Funcionario funcionario = new Funcionario();

        funcionario.setNomeFunc(request.getParameter("nomeFunc"));
        funcionario.setCpfFunc(request.getParameter("cpfFunc"));
        funcionario.setEmailFunc(request.getParameter("emailFunc"));
        funcionario.setTelefoneFunc(request.getParameter("telefoneFunc"));
        funcionario.setMatriculaFunc(request.getParameter("matriculaFunc"));

        funcionario.setIdCargo(parseIntOrZero(request.getParameter("idCargo")));
        funcionario.setIdSetor(parseIntOrZero(request.getParameter("idSetor")));

        funcionario.setIdDepto(parseIntOrZero(request.getParameter("idDepto")));

        return funcionario;
    }
    private int parseIntOrZero(String param) {
        if (param == null || param.trim().isEmpty()) {
            return 0;  // ou lança uma exceção controlada se for obrigatório
        }
        return Integer.parseInt(param);
    }


}
