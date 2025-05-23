package br.com.artvision.controllers;

import br.com.artvision.models.Usuario;
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
                // Criar objeto Usuario com todos os dados
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmpresa(rs.getString("empresa"));

                // Armazenar o objeto Usuario completo na sessão
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);

                // Redirecionar para a página correta do sistema
                response.sendRedirect(request.getContextPath() + "/sistema/sistema.jsp");
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
            response.sendRedirect(request.getContextPath() + "/sistema/sistema.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
