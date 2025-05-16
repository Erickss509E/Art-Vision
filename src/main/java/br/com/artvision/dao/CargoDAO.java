package br.com.artvision.dao;

import br.com.artvision.database.Conexao;
import br.com.artvision.models.Cargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CargoDAO    {

    public boolean cadastrar(Cargo cargo) {
        String sql = "INSERT INTO cargos (nome_cargo, id_setor) VALUES (?, ?)";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cargo.getNome());
            stmt.setInt(2, cargo.getIdSetor());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
