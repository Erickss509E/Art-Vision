package br.com.artvision.controllers;

import br.com.artvision.dao.SetorDAO;
import br.com.artvision.models.Setor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/setor")
public class SetorController extends HttpServlet {

    private SetorDAO setorDAO;

    @Override
    public void init() {
        setorDAO = new SetorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        try {
            switch (acao != null ? acao : "") {
                case "editar":
                    mostrarFormularioEdicao(request, response);
                    break;
                case "excluir":
                    excluirSetor(request, response);
                    break;
                default:
                    listarSetores(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        try {
            switch (acao != null ? acao : "") {
                case "cadastrar":
                    cadastrarSetor(request, response);
                    break;
                case "atualizar":
                    atualizarSetor(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação inválida");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void cadastrarSetor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String ala = request.getParameter("ala");

        Setor setor = new Setor(nome, ala);
        boolean sucesso = setorDAO.cadastrar(setor);

        if (sucesso) {
            response.sendRedirect("setor");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar setor.");
        }
    }

    private void listarSetores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Setor> setores = setorDAO.listar();
        request.setAttribute("setores", setores);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listar_setores.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Setor setor = setorDAO.buscarPorId(id);
        request.setAttribute("setor", setor);
        RequestDispatcher dispatcher = request.getRequestDispatcher("form_editar_setor.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizarSetor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String ala = request.getParameter("ala");

        Setor setor = new Setor();
        setor.setId(id);
        setor.setNome(nome);
        setor.setAla(ala);

        boolean sucesso = setorDAO.atualizar(setor);

        if (sucesso) {
            response.sendRedirect("setor");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar setor.");
        }
    }

    private void excluirSetor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean sucesso = setorDAO.excluir(id);

        if (sucesso) {
            response.sendRedirect("setor");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir setor.");
        }
    }
}
