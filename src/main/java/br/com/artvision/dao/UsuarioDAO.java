package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static final String INSERT_USUARIO_SQL = "INSERT INTO usuarios (nome, email, senha_usuario, cpf, empresa) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_USUARIOS_SQL = "SELECT * FROM usuarios";
    private static final String SELECT_USUARIO_BY_ID = "SELECT * FROM usuarios WHERE id_usuario = ?";
    private static final String UPDATE_USUARIO_SQL = "UPDATE usuarios SET nome = ?, email = ?, senha_usuario = ?, cpf = ?, empresa = ? WHERE id_usuario = ?";
    private static final String DELETE_USUARIO_SQL = "DELETE FROM usuarios WHERE id_usuario = ?";

    public boolean cadastrarUsuario(Usuario usuario) {
        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_USUARIO_SQL)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getEmpresa());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> listarUsuario() {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_USUARIOS_SQL)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha_usuario"),
                        rs.getString("cpf"),
                        rs.getString("empresa")
                );
                usuarios.add(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario buscarUsuarioPorId(int id_usuario) {
        Usuario usuario = null;

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_USUARIO_BY_ID)) {

            stmt.setInt(1, id_usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha_usuario"),
                            rs.getString("cpf"),
                            rs.getString("empresa")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public boolean atualizarUsuario(Usuario usuario) {
        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_USUARIO_SQL)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getEmpresa());
            stmt.setInt(6, usuario.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirUsuario(int id_usuario) {
        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_USUARIO_SQL)) {

            stmt.setInt(1, id_usuario);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
