package br.com.artvision.dao;

import br.com.artvision.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UsuarioDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/artvision";
    private String jdbcUsername = "root";
    private String jdbcPassword = "TbX77HHVdbXWca"; // substitua aqui

    private static final String INSERT_USUARIO_SQL = "INSERT INTO usuarios (nome, email, senha_usuario, cpf, empresa) VALUES (?, ?, ?, ?, ?)";

    public boolean cadastrar(Usuario usuario) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

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
}
