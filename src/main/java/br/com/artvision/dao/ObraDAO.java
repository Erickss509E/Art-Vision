package br.com.artvision.dao;

import br.com.artvision.models.Obra;
import br.com.artvision.database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ObraDAO {

    private Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        return factory.getConnection();
    }

    public void cadastrarObra(Obra obra) {
        String sql = "INSERT INTO obras (nome, descricao) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, obra.getNome());
            stmt.setString(2, obra.getDescricao());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Obra> listarObras() {
        List<Obra> obras = new ArrayList<>();
        String sql = "SELECT o.*, MAX(m.data_manutencao) AS ultima_manutencao " +
                     "FROM obras o LEFT JOIN manutencoes m ON o.id = m.id_obra " +
                     "GROUP BY o.id, o.nome, o.nome_autor, o.data_entrada_museu, o.valor_estimado, o.localizacao, o.descricao, o.area_museu";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Obra obra = new Obra();
                obra.setId(rs.getInt("id"));
                obra.setNome(rs.getString("nome"));
                obra.setNomeAutor(rs.getString("nome_autor"));
                obra.setDataEntradaMuseu(rs.getDate("data_entrada_museu"));
                obra.setValorEstimado(rs.getDouble("valor_estimado"));
                obra.setLocalizacao(rs.getString("localizacao"));
                obra.setDescricao(rs.getString("descricao"));
                java.util.Date ultimaManutencao = rs.getDate("ultima_manutencao");
                obra.setUltimaManutencao(ultimaManutencao);

                if (ultimaManutencao != null) {
                    long diff = (new java.util.Date().getTime() - ultimaManutencao.getTime()) / (1000 * 60 * 60 * 24);
                    obra.setTempoDesdeUltimaManutencao(diff);
                } else {
                    // Se não houver manutenção, calcular desde a data de entrada no museu
                    java.util.Date dataEntrada = obra.getDataEntradaMuseu();
                    if (dataEntrada != null) {
                        long diff = (new java.util.Date().getTime() - dataEntrada.getTime()) / (1000 * 60 * 60 * 24);
                        obra.setTempoDesdeUltimaManutencao(diff);
                    } else {
                        obra.setTempoDesdeUltimaManutencao(-1); // valor indicativo de ausência de dados
                    }
                }

                obras.add(obra);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obras;
    }

    public Obra buscarPorId(int id) {
        String sql = "SELECT * FROM obras WHERE id = ?";
        Obra obra = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    obra = new Obra();
                    obra.setId(rs.getInt("id"));
                    obra.setNome(rs.getString("nome"));
                    obra.setDescricao(rs.getString("descricao"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obra;
    }
}
