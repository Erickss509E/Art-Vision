package br.com.artvision.controllers;

import br.com.artvision.dao.SetorDAO;
import br.com.artvision.dto.SetorDTO;
import br.com.artvision.models.Setor;
import br.com.artvision.services.SetorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/setor")
public class SetorController extends HttpServlet {

    private SetorService setorService = new SetorService();

    @Override
    public void init() {
        setorService = new SetorService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            switch (action != null ? action : "") {
                case "listar":
                    listarSetores(request, response);
                    break;
                /*case "buscar":
                    buscarSetorPorId(request, response);
                    break;*/
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação GET não reconhecida.");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
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
                cadastrarSetor(request, response);
                break;
            case "atualizar":
                atualizarSetor(request, response);
                break;
            case "excluir":
                excluirSetor(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação POST não reconhecida.");
        }
    }

    private void cadastrarSetor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nome = request.getParameter("nome");
        String ala = request.getParameter("ala");

        Setor setor = new Setor(nome, ala);
        boolean sucesso = setorService.cadastrarSetor(setor);

        if (sucesso) {
            response.sendRedirect("setor");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar setor.");
        }
    }

    private void listarSetores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<SetorDTO> setores = setorService.listarSetor();
        request.setAttribute("setores", setores);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listar_setores.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizarSetor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String ala = request.getParameter("ala");

        Setor setor = new Setor();
        setor.setId(id);
        setor.setNome(nome);
        setor.setAla(ala);

        boolean sucesso = setorService.atualizarSetores(setor);

        if (sucesso) {
            response.sendRedirect("setor");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar setor.");
        }
    }

    private void excluirSetor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean sucesso = setorService.excluirSetor(id);

        if (sucesso) {
            response.sendRedirect("setor");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir setor.");
        }
    }
}
