package br.com.artvision.servlet.database;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("\"jdbc:mysql://localhost/artvision\", \"root\", \"1234\"");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
