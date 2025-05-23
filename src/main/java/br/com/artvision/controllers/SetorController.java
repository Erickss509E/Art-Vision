package br.com.artvision.controllers;

import br.com.artvision.dao.SetorDAO;
import br.com.artvision.dto.SetorDTO;
import br.com.artvision.models.Funcionario;
import br.com.artvision.models.Setor;
import br.com.artvision.services.SetorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/sistema/setores")
public class SetorController extends HttpServlet {

    private SetorService setorService;

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
                case "buscar":
                    buscarSetorParaEditar(request, response);
                    break;
                case "listar":
                case "":
                    listarSetores(request, response);
                    break;
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

        try {
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
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarSetores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<SetorDTO> setores = setorService.listarSetor();
        request.setAttribute("setores", setores);

        // Caminho absoluto
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sistema/setores.jsp");
        dispatcher.forward(request, response);
    }

    private void buscarSetorParaEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id_setor"));
            Setor setor = setorService.buscarSetoresPorId(id);

            if (setor != null) {
                List<SetorDTO> setores = setorService.listarSetor();
                request.setAttribute("setores", setores);
                request.setAttribute("setorEdicao", setor);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/sistema/setores.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Setor não encontrado.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        }
    }

    private void cadastrarSetor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String nome = request.getParameter("nomeSetor");
        String ala = request.getParameter("alaSetor");

        Setor setor = new Setor(nome, ala);
        boolean sucesso = setorService.cadastrarSetor(setor);

        if (sucesso) {
            response.sendRedirect(request.getContextPath() + "/sistema/setores.jsp");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar setor.");
        }
    }

    private void atualizarSetor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nomeSetor");
            String ala = request.getParameter("alaSetor");

            Setor setor = new Setor();
            setor.setId(id);
            setor.setNome(nome);
            setor.setAla(ala);

            boolean sucesso = setorService.atualizarSetores(setor);

            if (sucesso) {
                response.sendRedirect(request.getContextPath() + "/sistema/setores?action=listar");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar setor.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        }
    }

    private void excluirSetor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_setor"));
            SetorDAO setorDAO = new SetorDAO();
            
            if (setorDAO.temDependencias(id)) {
                String dependencias = setorDAO.listarDependencias(id);
                String mensagem = "Não é possível excluir o setor pois existem os seguintes itens vinculados a ele:\n\n" + 
                                dependencias + 
                                "\nPor favor, remova primeiro estes vínculos antes de excluir o setor.";
                
                request.getSession().setAttribute("erro", mensagem);
                response.sendRedirect(request.getContextPath() + "/sistema/setores?action=listar");
                return;
            }

            boolean sucesso = setorService.excluirSetor(id);
            if (sucesso) {
                response.sendRedirect(request.getContextPath() + "/sistema/setores?action=listar");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir setor.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        }
    }
}
