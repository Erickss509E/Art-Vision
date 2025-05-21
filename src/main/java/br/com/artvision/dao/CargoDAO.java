package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Cargo;
import br.com.artvision.models.Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO    {

    public boolean cadastrarCargo(Cargo cargo) {

        String sql = "INSERT INTO cargos (nome_cargo, id_setor) VALUES (?, ?)";

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
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

    public List<Cargo> listarCargos() {
        List<Cargo> cargos = new ArrayList<>();
        String sql = "SELECT * FROM cargos";

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cargo crgs = new Cargo(
                        rs.getInt("id_cargo"),
                        rs.getString("nome_cargo"),
                        rs.getInt("id_setor")
                );
                cargos.add(crgs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cargos;
    }

    public Cargo buscarCargosPorId(int id) {
        Cargo cargo = null;
        String sql = "SELECT * FROM cargos WHERE id_cargo = ?";

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cargo = new Cargo(
                            rs.getInt("id_cargo"),
                            rs.getString("nome_cargo"),
                            rs.getInt("id_setor ")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cargo;
    }

    public boolean atualizarCargo(Cargo cargo) {

        String sql = "UPDATE cargos SET nome_cargo=?, id_setor=? WHERE id_setor=?";

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cargo.getNome());
            stmt.setString(2, cargo.getNome());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirCargo(int id_cargo) {

        String sql = "DELETE FROM departamentos WHERE id_cargo = ?";

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_cargo);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
