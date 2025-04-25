package br.com.artvision.servlet;
import br.com.artvision.dao.UsuarioDAO;
import br.com.artvision.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/cadastrar-usuario")
public class CadastroUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String empresa = request.getParameter("empresa");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setEmpresa(empresa);
        usuario.setCpf(cpf);

        UsuarioDAO dao = new UsuarioDAO();
        dao.cadastrarUsuario(usuario);

        response.sendRedirect("login.jsp");
    }
}
