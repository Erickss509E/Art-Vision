package br.com.artvision.dao;

import br.com.artvision.model.Funcionario;
import br.com.artvision.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class UsuarioDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/artvision";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1234"; // substitua aqui

    private static final String INSERT_USUARIO_SQL = "INSERT INTO usuarios (nome, email, senha_usuario, cpf, empresa) VALUES (?, ?, ?, ?, ?)";

    public boolean cadastrar(Usuario usuario) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USUARIO_SQL);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setString(4, usuario.getCpf());
            preparedStatement.setString(5, usuario.getEmpresa());

            int rowsInserted = preparedStatement.executeUpdate();

            connection.close();

            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String SELECT_ALL_USUARIOS_SQL = "SELECT * FROM usuarios";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USUARIOS_SQL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha_usuario");
                String cpf = resultSet.getString("cpf");
                String empresa = resultSet.getString("empresa");

                Usuario usuario = new Usuario(id, nome, email, senha, cpf, empresa);
                usuarios.add(usuario);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario buscarPorId(int id) {
        String SELECT_USUARIO_BY_ID = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USUARIO_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha_usuario"),
                        rs.getString("cpf"),
                        rs.getString("empresa")
                );
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public boolean atualizar(Usuario usuario) {
        String UPDATE_USUARIO_SQL = "UPDATE usuarios SET nome = ?, email = ?, senha_usuario = ?, cpf = ?, empresa = ? WHERE id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USUARIO_SQL);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setString(4, usuario.getCpf());
            preparedStatement.setString(5, usuario.getEmpresa());
            preparedStatement.setInt(6, usuario.getId());

            int rowsUpdated = preparedStatement.executeUpdate();

            connection.close();

            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        String DELETE_USUARIO_SQL = "DELETE FROM usuarios WHERE id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USUARIO_SQL);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            connection.close();

            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
