package br.com.artvision.controllers;

import br.com.artvision.dao.EstadoConservacaoDAO;
import br.com.artvision.models.EstadoConservacao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/alertaManutencao")
public class AlertaManutencaoServlet extends HttpServlet {

    private EstadoConservacaoDAO estadoDAO = new EstadoConservacaoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<EstadoConservacao> todosEstados = estadoDAO.listarTodos();

        Date hoje = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(hoje);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        Date limite = cal.getTime();

        List<EstadoConservacao> proximosAlertas = todosEstados.stream()
                .filter(e -> e.getDataProximaManutencao() != null)
                .filter(e -> !e.getDataProximaManutencao().before(hoje) && !e.getDataProximaManutencao().after(limite))
                .collect(Collectors.toList());

        request.setAttribute("alertas", proximosAlertas);
        request.getRequestDispatcher("/Sistema/alertaManutencao.jsp").forward(request, response);
    }
}
