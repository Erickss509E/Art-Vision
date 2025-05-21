package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Funcionario;
import br.com.artvision.models.Manutencao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManutencaoDAO {

    public boolean cadastrarManu(Manutencao manutencao) {

        String sql = "INSERT INTO manutencoes (nome_manutencao, data_manutencao, observacao, id_obra, id_func, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, manutencao.getNomeManutencao());
            stmt.setDate(2, java.sql.Date.valueOf(manutencao.getDataManutencao()));
            stmt.setString(3, manutencao.getObservacao());
            stmt.setInt(4, manutencao.getIdObra());
            stmt.setInt(5, manutencao.getIdFunc());
            stmt.setInt(6, manutencao.getIdUsuario());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarManu(Manutencao manutencao) {

        String sql = "UPDATE manutencoes SET nome_manutencao=?, data_manutencao=?, observacao=?, id_obra=?, id_func=?, id_usuario=? WHERE id_manutencao=?";

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manutencao.getNomeManutencao());
            stmt.setDate(2, java.sql.Date.valueOf(manutencao.getDataManutencao()));
            stmt.setString(3, manutencao.getObservacao());
            stmt.setInt(4, manutencao.getIdObra());
            stmt.setInt(5, manutencao.getIdFunc());
            stmt.setInt(6, manutencao.getIdUsuario());
            stmt.setInt(7, manutencao.getIdManutencao());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Manutencao buscarManutencaoPorId(int id_obra) {
        Manutencao manutencao = null;
        String  sql = "SELECT * FROM manutencoes WHERE id_obra = ?";

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id_obra);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    manutencao = new Manutencao(
                            rs.getInt("id_manutencao"),
                            rs.getString("nome_manutencao"),
                            rs.getDate("data_manutencao").toLocalDate(),
                            rs.getString("observacao"),
                            rs.getInt("id_obra"),
                            rs.getInt("id_func"),
                            rs.getInt("id_usuario")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return manutencao;
    }

    public List<Manutencao> listarManu() {
        List<Manutencao> manutencoes = new ArrayList<>();
        String sql = "SELECT * FROM manutencoes WHERE data_manutencao <= ?";

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Manutencao m = new Manutencao();
                    m.setIdManutencao(rs.getInt("id_manutencao"));
                    m.setNomeManutencao(rs.getString("nome_manutencao"));
                    m.setDataManutencao(rs.getDate("data_manutencao").toLocalDate());
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

        String sql = "DELETE FROM manutencoes WHERE id_manutencao = ?";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
