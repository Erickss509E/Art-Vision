package br.com.artvision.database;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolConfig {
    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/artvision");
            dataSource.setUsername("root"); // substitua seu usuario caso seja necessario
            dataSource.setPassword("1234"); // substitua aqui sua senha

            dataSource.setMinIdle(5);
            dataSource.setMaxIdle(10);
            dataSource.setMaxTotal(20);

            System.out.println("Sucesso na conexão com o banco de dados!");
        }
        return dataSource;
    }
    public static Connection getConnection() throws SQLException {

        return getDataSource().getConnection();

    }
}

