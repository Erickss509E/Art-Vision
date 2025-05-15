package br.com.artvision.servlet;

import br.com.artvision.dao.UsuarioDAO;
import br.com.artvision.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditarUsuarioServlet extends HttpServlet {

    @Override //esperar o murilo finalizar o front
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.buscarPorId(id);

        req.setAttribute("usuario", usuario);
        RequestDispatcher dispatcher = req.getRequestDispatcher("form_editar_usuario.jsp"); //esperar o murilo terminar o front
        dispatcher.forward(req, resp);
    }
}
