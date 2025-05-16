package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Manutencao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManutencaoDAO {

    private static String INSERT_INTO_SQL = "INSERT INTO manutencoes (nome_manutencao, data_manutencao, observacao, id_obra, id_func, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
    private static String  SELECT_BY_ID_SQL = "SELECT * FROM manutencoes WHERE id_obra = ?";
    private static String SELECT_BY_MANU_SQL = "SELECT * FROM manutencoes WHERE data_manutencao <= ?";
    private static String DELETE_MANU_SQL = "DELETE FROM manutecoes WHERE id = ?";


    public void cadastrarManu(Manutencao manutencao) {

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_INTO_SQL)) {

            stmt.setString(1, manutencao.getNomeManutencao());
            stmt.setDate(2, new java.sql.Date(manutencao.getDataManutencao().getTime()));
            stmt.setString(3, manutencao.getObservacao());
            stmt.setInt(4, manutencao.getIdObra());
            stmt.setInt(5, manutencao.getIdFunc());
            stmt.setInt(6, manutencao.getIdUsuario());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Manutencao> listarPorObra(int idObra) {
        List<Manutencao> manutencoes = new ArrayList<>();


        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {

            stmt.setInt(1, idObra);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Manutencao m = new Manutencao();
                    m.setIdManutencao(rs.getInt("id_manutencao"));
                    m.setNomeManutencao(rs.getString("nome_manutencao"));
                    m.setDataManutencao(rs.getDate("data_manutencao"));
                    m.setObservacao(rs.getString("observacao"));
                    m.setIdObra(rs.getInt("id_obra"));
                    m.setIdFunc(rs.getInt("id_func"));
                    m.setIdUsuario(rs.getInt("id_usuario"));
                    manutencoes.add(m);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return manutencoes;
    }

    public List<Manutencao> listarManu(java.util.Date dataLimite) {
        List<Manutencao> manutencoes = new ArrayList<>();


        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_MANU_SQL)) {

            stmt.setDate(1, new java.sql.Date(dataLimite.getTime()));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Manutencao m = new Manutencao();
                    m.setIdManutencao(rs.getInt("id_manutencao"));
                    m.setNomeManutencao(rs.getString("nome_manutencao"));
                    m.setDataManutencao(rs.getDate("data_manutencao"));
                    m.setObservacao(rs.getString("observacao"));
                    m.setIdObra(rs.getInt("id_obra"));
                    m.setIdFunc(rs.getInt("id_func"));
                    m.setIdUsuario(rs.getInt("id_usuario"));
                    manutencoes.add(m);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return manutencoes;
    }
    public boolean excluirManu(int id) {
        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_MANU_SQL)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
