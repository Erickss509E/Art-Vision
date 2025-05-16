package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Cargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CargoDAO    {

    private static String INSERT_INTO_SQL = "INSERT INTO cargos (nome_cargo, id_setor) VALUES (?, ?)";

    public boolean cadastrarCargo(Cargo cargo) {

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_INTO_SQL)) {

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
