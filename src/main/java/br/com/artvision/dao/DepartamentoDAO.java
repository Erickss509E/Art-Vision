package br.com.artvision.dao;

import br.com.artvision.models.Departamento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DepartamentoDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/artvision";
    private String jdbcUserName = "root";
    private String jdbcPassword = "1234";

    private static final String INSERT_DEPARTAMENTO_SQL =
            "INSERT INTO departamentos (nome_depto, id_setor) VALUES (?, ?)";

    public boolean cadastrarDepartamento(Departamento departamento) {
        try (
                Connection connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEPARTAMENTO_SQL)
        ) {
            preparedStatement.setString(1, departamento.getNomeDepto());
            preparedStatement.setString(2, departamento.getIdSetor());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}