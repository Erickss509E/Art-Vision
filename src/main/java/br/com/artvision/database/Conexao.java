package br.com.artvision.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/artvision";
    private static final String USUARIO = "root";
    private static final String SENHA = "TbX77HHVdbXWca"; // substitua pela sua

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
