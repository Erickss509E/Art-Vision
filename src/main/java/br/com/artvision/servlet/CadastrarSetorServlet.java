package br.com.artvision.servlet;

import br.com.artvision.dao.SetorDAO;
import br.com.artvision.model.Setor;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class CadastrarSetorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String ala = req.getParameter("ala");

        Setor setor = new Setor();
        setor.setNome(nome);
        setor.setAla(ala);

        boolean sucesso = new SetorDAO().cadastrar(setor);
        resp.setContentType("text/plain");

        if (sucesso) {
            resp.getWriter().write("Setor cadastrado com sucesso!");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Erro ao cadastrar setor!");
        }
    }
}
