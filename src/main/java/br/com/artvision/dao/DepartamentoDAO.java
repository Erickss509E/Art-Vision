package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Departamento;
import br.com.artvision.models.Funcionario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {

    private static final String INSERT_DEPARTAMENTO_SQL =
            "INSERT INTO departamentos (nome_depto, id_setor) VALUES (?, ?)";
    private static final String SELECT_DEPARTAMENTO_SQL = "SELECT * FROM departamentos";
    private static final String SELECT_DEPTO_BY_ID_SQL = "SELECT * FROM departamentos WHERE id_depto = ?";
    private static final String UPDATE_DEPTO_SQL = "UPDATE departamentos SET nome_depto=?, id_setor=?";
    private static final String DELETE_DEPTO_SQL = "DELETE FROM departamentos WHERE id_depto = ?";

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
        public List<Departamento> listarDepartamentos() {
            List<Departamento> departamentos = new ArrayList<>();

            try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(SELECT_DEPARTAMENTO_SQL);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Departamento depto = new Departamento(
                            rs.getInt("id_depto"),
                            rs.getString("nome_depto"),
                            rs.getInt("id_setor")
                    );
                    departamentos.add(depto);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return departamentos;
        }

    public Departamento buscarDepartamentoPorId(int id) {
        Departamento departamento = null;

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_DEPTO_BY_ID_SQL)){

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    departamento = new Departamento(
                            rs.getInt("id_depto"),
                            rs.getString("nome_depto"),
                            rs.getInt("id_setor")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departamento;
    }

    public boolean atualizarDepartamento(Departamento departamento) {

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_DEPTO_SQL)) {

            stmt.setString(1, departamento.getNomeDepto());
            stmt.setString(2, departamento.getIdSetor());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirDepartamento(int id_depto) {

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_DEPTO_SQL)) {

            stmt.setInt(1, id_depto);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    }