package br.com.artvision.controllers;

import br.com.artvision.dao.CargoDAO;
import br.com.artvision.models.Cargo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class CadastrarCargoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String nomeCargo = request.getParameter("nome");
        String idSetorStr = request.getParameter("setor");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        if (nomeCargo == null || idSetorStr == null || nomeCargo.isEmpty() || idSetorStr.isEmpty()) {
            out.println("<p>Parâmetros inválidos!</p>");
            out.println("<a href='cadastrar-cargo.html'>Voltar</a>");
        } else {
            try {
                int idSetor = Integer.parseInt(idSetorStr);
                Cargo cargo = new Cargo();
                cargo.setNome(nomeCargo);
                cargo.setIdSetor(idSetor);

                CargoDAO cargoDAO = new CargoDAO();
                boolean sucesso = cargoDAO.cadastrar(cargo);

                if (sucesso) {
                    out.println("<p>Cargo cadastrado com sucesso!</p>");
                } else {
                    out.println("<p>Erro ao cadastrar o cargo!</p>");
                }
                out.println("<a href='cadastrar-cargo.html'>Voltar</a>");
            } catch (NumberFormatException e) {
                out.println("<p>ID do setor inválido!</p>");
                out.println("<a href='cadastrar-cargo.html'>Voltar</a>");
            }
        }

        out.println("</body></html>");
    }
}

