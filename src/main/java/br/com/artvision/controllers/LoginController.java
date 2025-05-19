package br.com.artvision.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        try {
            // Conectar ao banco (ajuste os dados da conexão)
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/artvision", "root", "1234");

            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM usuarios WHERE email = ? AND senha = ?");
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                response.getWriter().println("Login realizado com sucesso!");
                response.sendRedirect("/funcionario");
            } else {
                response.getWriter().println("Email ou senha inválidos.");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Erro no login: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
