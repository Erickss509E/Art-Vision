package br.com.artvision.database;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolConfig {
    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource() {
        if (dataSource == null) {
            System.out.println("Configurando conexão com o banco de dados...");
            dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/artvision");
            dataSource.setUsername("root"); // substitua seu usuario caso seja necessario
            dataSource.setPassword("1234"); // substitua aqui sua senha

            dataSource.setMinIdle(5);
            dataSource.setMaxIdle(10);
            dataSource.setMaxTotal(20);

            try {
                Connection testConn = dataSource.getConnection();
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
                testConn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return dataSource;
    }
    public static Connection getConnection() throws SQLException {
        Connection conn = getDataSource().getConnection();
        System.out.println("Nova conexão obtida do pool");
        return conn;
    }
}

