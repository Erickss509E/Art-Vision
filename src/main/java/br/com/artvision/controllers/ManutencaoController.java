package br.com.artvision.controllers;

import br.com.artvision.dao.ManutencaoDAO;
import br.com.artvision.models.Manutencao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/manutencao")
public class ManutencaoServlet extends HttpServlet {

    private ManutencaoDAO manutencaoDAO = new ManutencaoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String obraIdParam = request.getParameter("obraId");
        if (obraIdParam != null) {
            try {
                int obraId = Integer.parseInt(obraIdParam);
                List<Manutencao> manutencoes = manutencaoDAO.listarPorObra(obraId);
                request.setAttribute("manutencoes", manutencoes);
                request.getRequestDispatcher("/Sistema/listaManutencoes.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da obra inválido");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro obraId é obrigatório");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nomeManutencao = request.getParameter("nomeManutencao");
            String dataManutencaoStr = request.getParameter("dataManutencao");
            String observacao = request.getParameter("observacao");
            int idObra = Integer.parseInt(request.getParameter("idObra"));
            int idFunc = Integer.parseInt(request.getParameter("idFunc"));
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dataManutencao = sdf.parse(dataManutencaoStr);

            Manutencao manutencao = new Manutencao();
            manutencao.setNomeManutencao(nomeManutencao);
            manutencao.setDataManutencao(dataManutencao);
            manutencao.setObservacao(observacao);
            manutencao.setIdObra(idObra);
            manutencao.setIdFunc(idFunc);
            manutencao.setIdUsuario(idUsuario);

            manutencaoDAO.cadastrarManu(manutencao);

            response.sendRedirect(request.getContextPath() + "/manutencao?obraId=" + idObra);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar manutenção");
        }
    }
}
