package br.com.artvision.controllers;

import br.com.artvision.dao.UsuarioDAO;
import br.com.artvision.dto.UsuarioDTO;
import br.com.artvision.models.Usuario;
import br.com.artvision.services.UsuarioService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {

    private UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if (acao == null) {
            listar(req, resp);
            return;
        }

        switch (acao) {
            case "editar":
                editar(req, resp);
                break;
            case "excluir":
                excluir(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if (acao == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação não informada.");
            return;
        }

        switch (acao) {
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "atualizar":
                atualizar(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        req.setAttribute("usuarios", usuarios);
        RequestDispatcher dispatcher = req.getRequestDispatcher("listar_usuarios.jsp");
        dispatcher.forward(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Usuario usuario = usuarioService.buscarPorId(id);
        req.setAttribute("usuario", usuario);
        RequestDispatcher dispatcher = req.getRequestDispatcher("form_editar_usuario.jsp");
        dispatcher.forward(req, resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Usuario usuario = new Usuario();
        usuario.setNome(req.getParameter("nome"));
        usuario.setEmail(req.getParameter("email"));
        usuario.setSenha(req.getParameter("senha"));
        usuario.setCpf(req.getParameter("cpf"));
        usuario.setEmpresa(req.getParameter("empresa"));

        boolean sucesso = usuarioService.cadastrarUsuario(usuario);

        if (sucesso) {
            resp.sendRedirect("usuario");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar usuário.");
        }
    }

    private void atualizar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Usuario usuario = new Usuario(
                id,
                req.getParameter("nome"),
                req.getParameter("email"),
                req.getParameter("senha"),
                req.getParameter("cpf"),
                req.getParameter("empresa")
        );

        boolean sucesso = usuarioService.atualizarUsuario(usuario);

        if (sucesso) {
            resp.sendRedirect("usuario");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar usuário.");
        }
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean sucesso = usuarioService.excluirUsuario(id);

        if (sucesso) {
            resp.sendRedirect("usuario");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir usuário.");
        }
    }
}
