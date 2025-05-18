package br.com.artvision.controllers;

import br.com.artvision.dto.ManutencaoDTO;
import br.com.artvision.models.Manutencao;
import br.com.artvision.services.ManutencaoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/manutencao") // mudar aqui front
public class ManutencaoController extends HttpServlet {

    private ManutencaoService manutencaoService;

    @Override
    public void init() {
        manutencaoService = new ManutencaoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) action = "listar";

        switch (action) {
            case "listar":
                listarManu(request, response);
                break;

            case "buscar":
                buscarManutencaoPorObra(request, response);
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
                cadastrarManutencao(request, response);
                break;

            case "atualizar":
                atualizarManutencao(request, response);
                break;

            case "excluir":
                excluirManutencao(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação POST não reconhecida.");
        }

    }

    private void listarManu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ManutencaoDTO> lista = manutencaoService.listarManu();
        request.setAttribute("manutencao", lista); // aqui tbm
        request.getRequestDispatcher("manutencao").forward(request, response); // aqui tbm
    }

    private void buscarManutencaoPorObra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_obra"));
            Manutencao manutencao = manutencaoService.buscarPorObra(id);

            if (manutencao != null) {
                request.setAttribute("manutencao", manutencao); // aqui tbm
                request.getRequestDispatcher("manutencao").forward(request, response); // aqui tbm
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Manutenção não encontrada!");
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id inválido!");
        }
    }

    private void cadastrarManutencao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Manutencao manutencao = new Manutencao();

        manutencao.setNomeManutencao(request.getParameter("nome_manutencao"));

        String dataStr = request.getParameter("data_manutencao");
        System.out.println("Data recebida: " + dataStr);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate data = LocalDate.parse(dataStr, formatter);
            manutencao.setDataManutencao(data);
        } catch (DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de data inválido!");
            return;
        }

        manutencao.setObservacao(request.getParameter("observacao"));
        manutencao.setIdObra(Integer.parseInt(request.getParameter("id_obra")));
        manutencao.setIdFunc(Integer.parseInt(request.getParameter("id_func")));
        manutencao.setIdUsuario(Integer.parseInt(request.getParameter("id_usuario")));

        boolean sucesso = manutencaoService.cadastrarManu(manutencao);

        if (sucesso) {
            response.sendRedirect("manutencao"); // aqui tbm
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar uma manutenção!");
        }
    }

    private void atualizarManutencao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Manutencao manutencao = new Manutencao();

        manutencao.setNomeManutencao(request.getParameter("nome_manutencao"));

        String dataStr = request.getParameter("data_manutencao");
        System.out.println("Data recebida (atualizar): " + dataStr);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate data = LocalDate.parse(dataStr, formatter);
            manutencao.setDataManutencao(data);
        } catch (DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de data inválido!");
            return;
        }

        manutencao.setObservacao(request.getParameter("observacao"));
        manutencao.setIdObra(Integer.parseInt(request.getParameter("id_obra")));
        manutencao.setIdFunc(Integer.parseInt(request.getParameter("id_func")));
        manutencao.setIdUsuario(Integer.parseInt(request.getParameter("id_usuario")));

        boolean sucesso = manutencaoService.atualizarManutencao(manutencao);

        if (sucesso) {
            response.sendRedirect("manutencao"); // aqui tbm
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar manutenção!");
        }
    }

    private void excluirManutencao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id_manutencao"));
        boolean sucesso = manutencaoService.excluirManu(id);

        if (sucesso) {
            response.sendRedirect("manutencao"); // aqui tbm
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir uma manutenção!");
        }
    }
}
