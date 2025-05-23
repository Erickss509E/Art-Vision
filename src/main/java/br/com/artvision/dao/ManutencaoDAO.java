package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.dto.ManutencaoDTO;
import br.com.artvision.models.Funcionario;
import br.com.artvision.models.Manutencao;
import br.com.artvision.models.Obra;
import br.com.artvision.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManutencaoDAO {
    private Connection connection;

    public ManutencaoDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public List<Manutencao> listarTodos() {
        List<Manutencao> manutencoes = new ArrayList<>();
        String sql = "SELECT m.*, o.nome_obra, f.nome_func FROM manutencoes m " +
                    "INNER JOIN obras o ON m.id_obra = o.id_obra " +
                    "INNER JOIN funcionarios f ON m.id_func = f.id_func";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Manutencao manutencao = new Manutencao();
                manutencao.setIdManutencao(rs.getInt("id_manutencao"));
                manutencao.setNomeManutencao(rs.getString("nome_manutencao"));
                manutencao.setDataManutencao(rs.getDate("data_manutencao").toLocalDate());
                manutencao.setObservacao(rs.getString("observacao"));
                manutencao.setIdObra(rs.getInt("id_obra"));
                manutencao.setIdFunc(rs.getInt("id_func"));
                manutencao.setIdUsuario(rs.getInt("id_usuario"));
                
                manutencoes.add(manutencao);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar manutenções: " + e.getMessage(), e);
        }

        return manutencoes;
    }

    public Manutencao buscarPorId(int id) {
        String sql = "SELECT m.*, o.nome_obra, f.nome_func FROM manutencoes m " +
                    "INNER JOIN obras o ON m.id_obra = o.id_obra " +
                    "INNER JOIN funcionarios f ON m.id_func = f.id_func " +
                    "WHERE m.id_manutencao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Manutencao manutencao = new Manutencao();
                    manutencao.setIdManutencao(rs.getInt("id_manutencao"));
                    manutencao.setNomeManutencao(rs.getString("nome_manutencao"));
                    manutencao.setDataManutencao(rs.getDate("data_manutencao").toLocalDate());
                    manutencao.setObservacao(rs.getString("observacao"));
                    manutencao.setIdObra(rs.getInt("id_obra"));
                    manutencao.setIdFunc(rs.getInt("id_func"));
                    manutencao.setIdUsuario(rs.getInt("id_usuario"));
                    
                    return manutencao;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar manutenção: " + e.getMessage(), e);
        }

        return null;
    }

    public boolean cadastrarManu(Manutencao manutencao) {
        String sql = "INSERT INTO manutencoes (nome_manutencao, data_manutencao, observacao, id_obra, id_func, id_usuario) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, manutencao.getNomeManutencao());
            stmt.setDate(2, java.sql.Date.valueOf(manutencao.getDataManutencao()));
            stmt.setString(3, manutencao.getObservacao());
            stmt.setInt(4, manutencao.getIdObra());
            stmt.setInt(5, manutencao.getIdFunc());
            stmt.setInt(6, manutencao.getIdUsuario());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar manutenção: " + e.getMessage(), e);
        }
    }

    public boolean atualizarManu(Manutencao manutencao) {
        String sql = "UPDATE manutencoes SET nome_manutencao=?, data_manutencao=?, observacao=?, " +
                    "id_obra=?, id_func=?, id_usuario=? WHERE id_manutencao=?";

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
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar manutenção: " + e.getMessage(), e);
        }
    }

    public boolean excluirManu(int id) {
        String sql = "DELETE FROM manutencoes WHERE id_manutencao = ?";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir manutenção: " + e.getMessage(), e);
        }
    }
}
