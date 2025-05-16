package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Departamento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DepartamentoDAO {

    private static final String INSERT_DEPARTAMENTO_SQL =
            "INSERT INTO departamentos (nome_depto, id_setor) VALUES (?, ?)";

    public boolean cadastrarDepartamento(Departamento departamento) {
        try (
                Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
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