package br.com.artvision.controllers;

import br.com.artvision.dao.ManutencaoDAO;
import br.com.artvision.models.Manutencao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/calendarioManutencao")
public class CalendarioManutencaoServlet extends HttpServlet {

    private ManutencaoDAO manutencaoDAO = new ManutencaoDAO();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            
            Date hoje = new Date();

     
            Calendar cal = Calendar.getInstance();
            cal.setTime(hoje);
            cal.add(Calendar.YEAR, 1);
            Date limite = cal.getTime();

            List<Manutencao> manutencoesFuturas = manutencaoDAO.listarManutencoesProximas(limite);

            String json = gson.toJson(manutencoesFuturas);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar manutenções para o calendário");
        }
    }
}
