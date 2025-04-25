package br.com.artvision.servlet;

import br.com.artvision.dao.ObraDAO;
import br.com.artvision.model.Obra;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/obra-localizacao")
public class ObraLocalizacaoServlet extends HttpServlet {

    private ObraDAO obraDAO = new ObraDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Obra> obras = obraDAO.listarObras();
            String json = new Gson().toJson(obras);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar localizações das obras");
        }
    }
}
