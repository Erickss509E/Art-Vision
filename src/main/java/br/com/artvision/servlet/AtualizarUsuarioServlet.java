package br.com.artvision.servlet;

import br.com.artvision.dao.UsuarioDAO;
import br.com.artvision.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AtualizarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String cpf = req.getParameter("cpf");
        String empresa = req.getParameter("empresa");

        Usuario usuario = new Usuario(id, nome, email, senha, cpf, empresa);
        UsuarioDAO dao = new UsuarioDAO();
        boolean sucesso = dao.atualizar(usuario);

        if (sucesso) {
            resp.sendRedirect(req.getContextPath() + "/usuarios");//esperar o front com o murilo
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar usuário.");
        }
    }
}
