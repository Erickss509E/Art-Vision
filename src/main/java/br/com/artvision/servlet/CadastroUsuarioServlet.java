package br.com.artvision.servlet;

import br.com.artvision.dao.UsuarioDAO;
import br.com.artvision.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


public class CadastroUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String empresa = request.getParameter("empresa");

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setCpf(cpf);
        usuario.setEmpresa(empresa);

        UsuarioDAO dao = new UsuarioDAO();
        boolean sucesso = dao.cadastrar(usuario);

        if (sucesso) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar");
        }
    }
}
