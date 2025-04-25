package br.com.artvision.dao;

import br.com.artvision.model.EstadoConservacao;
import br.com.artvision.servlet.database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstadoConservacaoDAO {

    private Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        return factory.getConnection();
    }

    public void cadastrarEstadoConservacao(EstadoConservacao estado) {
        String sql = "INSERT INTO estado_conservacao (obra_id, descricao, imagens, historico_manutencao, data_proxima_manutencao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, estado.getObraId());
            stmt.setString(2, estado.getDescricao());
            stmt.setString(3, estado.getImagens());
            stmt.setString(4, estado.getHistoricoManutencao());
            if (estado.getDataProximaManutencao() != null) {
                stmt.setDate(5, new java.sql.Date(estado.getDataProximaManutencao().getTime()));
            } else {
                stmt.setDate(5, null);
            }

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EstadoConservacao> listarPorObra(int obraId) {
        List<EstadoConservacao> estados = new ArrayList<>();
        String sql = "SELECT * FROM estado_conservacao WHERE obra_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, obraId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    EstadoConservacao estado = new EstadoConservacao();
                    estado.setId(rs.getInt("id"));
                    estado.setObraId(rs.getInt("obra_id"));
                    estado.setDescricao(rs.getString("descricao"));
                    estado.setImagens(rs.getString("imagens"));
                    estado.setHistoricoManutencao(rs.getString("historico_manutencao"));
                    estado.setDataProximaManutencao(rs.getDate("data_proxima_manutencao"));
                    estados.add(estado);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estados;
    }

    public List<EstadoConservacao> listarTodos() {
        List<EstadoConservacao> estados = new ArrayList<>();
        String sql = "SELECT * FROM estado_conservacao";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EstadoConservacao estado = new EstadoConservacao();
                estado.setId(rs.getInt("id"));
                estado.setObraId(rs.getInt("obra_id"));
                estado.setDescricao(rs.getString("descricao"));
                estado.setImagens(rs.getString("imagens"));
                estado.setHistoricoManutencao(rs.getString("historico_manutencao"));
                estado.setDataProximaManutencao(rs.getDate("data_proxima_manutencao"));
                estados.add(estado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estados;
    }
}
