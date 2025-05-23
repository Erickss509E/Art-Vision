package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Setor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SetorDAO {

    public boolean cadastrarSetor(Setor setor) {
        String sql = "INSERT INTO setores (nome_setor, ala) VALUES (?, ?)";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, setor.getNome());
            stmt.setString(2, setor.getAla());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Setor> listarSetor() {
        List<Setor> setores = new ArrayList<>();
        String sql = "SELECT id_setor, nome_setor, ala FROM setores";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Setor setor = new Setor(
                        rs.getString("nome_setor"),
                        rs.getString("ala")
                );
                setor.setId(rs.getInt("id_setor"));
                setores.add(setor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return setores;
    }

    public Setor buscarSetorPorId(int id_setor) {
        Setor setor = null;
        String sql = "SELECT id_setor, nome_setor, ala FROM setores WHERE id_setor = ?";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id_setor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    setor = new Setor(
                            rs.getString("nome_setor"),
                            rs.getString("ala")
                    );
                    setor.setId(rs.getInt("id_setor"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return setor;
    }

    public boolean atualizarSetor(Setor setor) {
        String sql = "UPDATE setores SET nome_setor = ?, ala = ? WHERE id_setor = ?";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

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
        String sql = "DELETE FROM setores WHERE id_setor = ?";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id_setor);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
