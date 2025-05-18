package br.com.artvision.controllers;

import br.com.artvision.dao.FuncionarioDAO;
import br.com.artvision.dto.FuncionarioDTO;
import br.com.artvision.models.Funcionario;
import br.com.artvision.services.FuncionarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/funcionario") //mudar aqui nome da página front
public class FuncionarioController extends HttpServlet {

    private FuncionarioService funcionarioService = new FuncionarioService();

    @Override
    public void init() {
        funcionarioService = new FuncionarioService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "listar":
                listarFuncionarios(request, response);
                break;

            case "buscar":
                buscarFuncionarioPorId(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação GET não reconhecida.");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro 'action' é obrigatório.");
            return;
        }

        switch (action) {
            case "cadastrar":
                cadastrarFuncionario(request, response);
                break;
            case "atualizar":
                atualizarFuncionario(request, response);
                break;
            case "excluir":
                excluirFuncionario(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação POST não reconhecida.");
        }
    }

    private void listarFuncionarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<FuncionarioDTO> lista = funcionarioService.listarFuncionarios();
        request.setAttribute("funcionario", lista); //mudar aqui nome da página front
        request.getRequestDispatcher("funcionario").forward(request, response); // mudar aqui nome da página front
    }

    private void buscarFuncionarioPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_func"));
            Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(id);

            if (funcionario != null) {
                request.setAttribute("funcionario", funcionario); //mudar aqui nome da página front
                request.getRequestDispatcher("editar-funcionario.html").forward(request, response); //aqui tbm
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Funcionário não encontrado.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        }
    }

    private void cadastrarFuncionario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpfFunc(request.getParameter("cpf_func"));
        funcionario.setNomeFunc(request.getParameter("nome_func"));
        funcionario.setTelefoneFunc(request.getParameter("telefone_func"));
        funcionario.setEmailFunc(request.getParameter("email_func"));
        funcionario.setIdCargo(Integer.parseInt(request.getParameter("id_cargo")));
        funcionario.setIdSetor(Integer.parseInt(request.getParameter("id_setor")));
        funcionario.setIdDepto(Integer.parseInt(request.getParameter("id_depto")));

        boolean sucesso = funcionarioService.cadastrarFuncionario(funcionario);
        if (sucesso) {
            response.sendRedirect("/funcionario"); // aqui tbm
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar funcionário!");
        }
    }

    private void atualizarFuncionario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFunc(Integer.parseInt(request.getParameter("id_func")));
        funcionario.setCpfFunc(request.getParameter("cpf_func"));
        funcionario.setNomeFunc(request.getParameter("nome_func"));
        funcionario.setTelefoneFunc(request.getParameter("telefone_func"));
        funcionario.setEmailFunc(request.getParameter("email_func"));
        funcionario.setIdCargo(Integer.parseInt(request.getParameter("id_cargo")));
        funcionario.setIdSetor(Integer.parseInt(request.getParameter("id_setor")));
        funcionario.setIdDepto(Integer.parseInt(request.getParameter("id_depto")));

        boolean sucesso = funcionarioService.atualizarFuncionario(funcionario);
        if (sucesso) {
            response.sendRedirect("/funcionario"); // aqui tbm
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar funcionário!");
        }
    }

    private void excluirFuncionario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id_func"));
        boolean sucesso = funcionarioService.excluirFuncionario(id);

        if (sucesso) {
            response.sendRedirect("/funcionario"); // aqui tbm
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir funcionário!");
        }
    }
}