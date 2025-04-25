package br.com.artvision.dao;

import br.com.artvision.model.Cargo;
import br.com.artvision.servlet.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {

    private Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        return factory.getConnection();
    }

    public void cadastrarCargo(Cargo cargo) {
        String sql = "INSERT INTO cargos (nome_cargo) VALUES (?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cargo.getNomeCargo());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cargo> listarCargos() {
        List<Cargo> cargos = new ArrayList<>();
        String sql = "SELECT * FROM cargos";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cargo c = new Cargo();
                c.setIdCargo(rs.getInt("id_cargo"));
                c.setNomeCargo(rs.getString("nome_cargo"));
                cargos.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cargos;
    }

    public void atualizarCargo(Cargo cargo) {
        String sql = "UPDATE cargos SET nome_cargo = ? WHERE id_cargo = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cargo.getNomeCargo());
            stmt.setInt(2, cargo.getIdCargo());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluirCargo(int idCargo) {
        String sql = "DELETE FROM cargos WHERE id_cargo = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCargo);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
