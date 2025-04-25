package br.com.artvision.servlet;

import br.com.artvision.dao.UsuarioDAO;
import br.com.artvision.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.autenticar(email, senha);

        if (usuario != null) {
            
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);
            response.sendRedirect("dashboard.jsp"); 
        } else {

            response.sendRedirect("login.jsp?erro=1");
        }
    }
}
