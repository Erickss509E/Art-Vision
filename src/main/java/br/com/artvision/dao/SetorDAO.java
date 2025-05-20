package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Setor;
import br.com.artvision.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SetorDAO {

    private static final String INSERT_INTO_SQL = "INSERT INTO setores (nome_setor, ala) VALUES (?, ?)";
    private static final String SELECT_ALL_SETORES_SQL = "SELECT * FROM setores";
    private static final String SELECT_BY_SETOR_ID_SQL = "SELECT * FROM setores WHERE id_setor = ?";
    private static final String UPDATE_SETOR_SQL = "UPDATE setores SET nome_setor = ?, ala = ? WHERE id_setor = ?";
    private static final String DELETE_SETOR_SQL = "DELETE FROM setores WHERE id_setor = ?";

    public boolean cadastrarSetor(Setor setor) {

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_INTO_SQL)) {

            stmt.setString(1, setor.getNome());
            stmt.setString(2, setor.getAla());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Setor> listarSetor() {
        List<Setor> setores = new ArrayList<>();

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SETORES_SQL)) {

            while (rs.next()) {
                Setor setor = new Setor(
                        rs.getString("nome"),
                        rs.getString("ala")
                );
                setores.add(setor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return setores;
    }

    public Setor buscarSetorPorId(int id_setor) {
        Setor setor = null;

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_BY_SETOR_ID_SQL)) {

            stmt.setInt(1, id_setor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    setor = new Setor(
                            rs.getString("nome_setor"),
                            rs.getString("ala")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return setor;
    }

    public boolean atualizarSetor(Setor setor) {
        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_SETOR_SQL)) {

            stmt.setString(1, setor.getNome());
            stmt.setString(2, setor.getAla());
            stmt.setInt(3, setor.getId());


            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirSetor(int id_setor) {
        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_SETOR_SQL)) {

            stmt.setInt(1, id_setor);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
