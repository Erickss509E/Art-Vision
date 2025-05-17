package br.com.artvision.services;

import br.com.artvision.dao.UsuarioDAO;
import br.com.artvision.dto.UsuarioDTO;
import br.com.artvision.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listarUsuario();
        List<UsuarioDTO> dtos = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioDTO dto = new UsuarioDTO(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getCpf(),
                    usuario.getEmpresa()
            );
            dtos.add(dto);
        }

        return dtos;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        // Aqui você pode aplicar regras de negócio antes de cadastrar
        // Por exemplo: validar CPF, verificar se e-mail já existe, criptografar senha, etc.
        return usuarioDAO.cadastrarUsuario(usuario);
    }

    public Usuario buscarPorId(int id) {
        return usuarioDAO.buscarUsuarioPorId(id);
    }

    public boolean atualizarUsuario(Usuario usuario) {
        return usuarioDAO.atualizarUsuario(usuario);
    }

    public boolean excluirUsuario(int id) {
        return usuarioDAO.excluirUsuario(id);
    }
}
