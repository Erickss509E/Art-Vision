package br.com.artvision.servlet;

import br.com.artvision.dao.UsuarioDAO;
import br.com.artvision.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListarUsuariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = dao.listar();

        req.setAttribute("usuarios", usuarios);
        RequestDispatcher dispatcher = req.getRequestDispatcher("listar_usuarios_jsp");
        dispatcher.forward(req, resp);
    }
}
