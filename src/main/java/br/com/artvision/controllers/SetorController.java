package br.com.artvision.controllers;

import br.com.artvision.dao.SetorDAO;
import br.com.artvision.models.Setor;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SetorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String ala = request.getParameter("ala");

        Setor setor = new Setor();
        setor.setNome(nome);
        setor.setAla(ala);

        boolean sucesso = new SetorDAO().cadastrar(setor);
        if (sucesso) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
