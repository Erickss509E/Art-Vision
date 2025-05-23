package br.com.artvision.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Artvision", "root", "1234");

            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM usuarios WHERE email = ? AND senha_usuario = ?");
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", email);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sistema/sistema.jsp"); // pessoal, essa é a funcionalidade que redireciona a página
                                                                                                                // para uma página privada
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("erroLogin", "Email ou senha inválidos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erroLogin", "Erro no login: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            request.getRequestDispatcher("/WEB-INF/sistema/funcionarios.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
