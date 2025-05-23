package br.com.artvision.controllers;

import br.com.artvision.dao.ManutencaoDAO;
import br.com.artvision.models.Manutencao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManutencaoController extends HttpServlet {
    private ManutencaoDAO manutencaoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        manutencaoDAO = new ManutencaoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        try {
            switch (action) {
                case "/novo":
                    showNovoForm(request, response);
                    break;
                case "/editar":
                    showEditForm(request, response);
                    break;
                case "/excluir":
                    excluirManutencao(request, response);
                    break;
                default:
                    listarManutencoes(request, response);
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("erro", "Ocorreu um erro: " + ex.getMessage());
            listarManutencoes(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        try {
            switch (action) {
                case "/salvar":
                    salvarManutencao(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/sistema/manutencao.jsp");
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("erro", "Ocorreu um erro: " + ex.getMessage());
            listarManutencoes(request, response);
        }
    }

    private void listarManutencoes(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Manutencao> manutencoes = manutencaoDAO.listarTodos();
        request.setAttribute("manutencoes", manutencoes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sistema/manutencao.jsp");
        dispatcher.forward(request, response);
    }

    private void showNovoForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sistema/manutencao-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Manutencao manutencao = manutencaoDAO.buscarPorId(id);
        request.setAttribute("manutencao", manutencao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sistema/manutencao-form.jsp");
        dispatcher.forward(request, response);
    }

    private void salvarManutencao(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Implementar lógica de salvamento
        response.sendRedirect(request.getContextPath() + "/sistema/manutencao.jsp");
    }

    private void excluirManutencao(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        manutencaoDAO.excluir(id);
        response.sendRedirect(request.getContextPath() + "/sistema/manutencao.jsp");
    }
}
