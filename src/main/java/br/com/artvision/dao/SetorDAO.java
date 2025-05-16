package br.com.artvision.dao;

import br.com.artvision.database.Conexao;
import br.com.artvision.models.Setor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetorDAO {

    public boolean cadastrar(Setor setor) {
        String sql = "INSERT INTO setores (nome_setor, ala) VALUES (?, ?)";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, setor.getNome());
            stmt.setString(2, setor.getAla());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
