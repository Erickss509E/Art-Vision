package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Obra;
import br.com.artvision.database.ConnectionFactory;
import br.com.artvision.models.Setor;
import com.sun.org.apache.xpath.internal.objects.XString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ObraDAO {
    private static final String insert_sql = "INSERT INTO obras (nome, descricao) VALUES (?, ?)";
    private static final String list_sql = "SELECT o.*, MAX(m.data_manutencao) AS ultima_manutencao " + "FROM obras o LEFT JOIN manutencoes m ON o.id = m.id_obra " + "GROUP BY o.id, o.nome, o.nome_autor, o.data_entrada_museu, o.valor_estimado, o.localizacao, o.descricao, o.area_museu";
    private static final String read_sql = "SELECT * FROM obras WHERE id = ?";

    public boolean cadastrar(Obra obra) {

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
        PreparedStatement stmt = connection.prepareStatement(insert_sql)) {

            stmt.setString(1, "nome");
            stmt.setString(2, "descricao");

            stmt.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Obra buscarPorId(int id) {
        Obra obra = null;

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(read_sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    obra = new Obra(
                            rs.getString("nome"),
                            rs.getString("descricao")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obra;
    }
}
