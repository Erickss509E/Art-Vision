package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Departamento;
import br.com.artvision.utils.CascadeDeleteUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {

    public boolean cadastrarDepartamento(Departamento departamento) {
        String sql = "INSERT INTO departamentos (nome_depto, id_setor) VALUES (?, ?)";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, departamento.getNomeDepto());
            preparedStatement.setInt(2, departamento.getIdSetor());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Departamento> listarDepartamentos() {
        List<Departamento> departamentos = new ArrayList<>();
        String sql = "SELECT d.id_depto, d.nome_depto, d.id_setor, s.nome_setor " +
                "FROM departamentos d " +
                "LEFT JOIN setores s ON d.id_setor = s.id_setor";

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Departamento depto = new Departamento(
                        rs.getInt("id_depto"),
                        rs.getString("nome_depto"),
                        rs.getInt("id_setor")
                );
                departamentos.add(depto);
                System.out.println("Departamento encontrado: ID=" + depto.getIdDepto() + ", Nome=" + depto.getNomeDepto());
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar departamentos: " + e.getMessage());
            e.printStackTrace();
        }
        return departamentos;
    }

    public Departamento buscarDepartamentoPorId(int id) {
        String sql = "SELECT * FROM departamentos WHERE id_depto = ?";
        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Departamento(
                            rs.getInt("id_depto"),
                            rs.getString("nome_depto"),
                            rs.getInt("id_setor")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean atualizarDepartamento(Departamento departamento) {
        String sql = "UPDATE departamentos SET nome_depto=?, id_setor=? WHERE id_depto=?";
        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, departamento.getNomeDepto());
            stmt.setInt(2, departamento.getIdSetor());
            stmt.setInt(3, departamento.getIdDepto());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirDepartamento(int idDepto) {
        Connection connection = null;
        try {
            connection = CascadeDeleteUtil.iniciarTransacao();
            boolean sucesso = CascadeDeleteUtil.deleteDepartamento(connection, idDepto);
            CascadeDeleteUtil.finalizarTransacao(connection, sucesso);
            return sucesso;
        } catch (SQLException e) {
            if (connection != null) {
                CascadeDeleteUtil.finalizarTransacao(connection, false);
            }
            System.out.println("Erro ao excluir departamento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
