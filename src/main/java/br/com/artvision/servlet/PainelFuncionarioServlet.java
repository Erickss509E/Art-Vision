package br.com.artvision.servlet;

import br.com.artvision.dao.FuncionarioDAO;
import br.com.artvision.model.FuncionarioCount;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/painelFuncionarios")
public class PainelFuncionarioServlet extends HttpServlet {

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<FuncionarioCount> counts = funcionarioDAO.contarFuncionariosPorSetorECargo();

            String json = gson.toJson(counts);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar dados do painel de funcionários");
        }
    }
}
