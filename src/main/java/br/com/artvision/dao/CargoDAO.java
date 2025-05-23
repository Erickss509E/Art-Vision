package br.com.artvision.dao;

import br.com.artvision.models.Cargo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {

    private Connection getConnection() throws SQLException {
        // Ajuste aqui conforme seu método de conexão
        // Exemplo básico:
        String url = "jdbc:mysql://localhost:3306/seu_banco";
        String user = "root";
        String password = "senha";
        return DriverManager.getConnection(url, user, password);
    }

    public List<Cargo> listarCargosComNomeSetor() {
        List<Cargo> lista = new ArrayList<>();

        String sql = "SELECT c.id, c.nome, c.id_setor, s.nome AS nome_setor " +
                "FROM cargos c " +
                "JOIN setores s ON c.id_setor = s.id";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setNome(rs.getString("nome"));
                cargo.setIdSetor(rs.getInt("id_setor"));
                cargo.setNomeSetor(rs.getString("nome_setor"));

                lista.add(cargo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Outros métodos: buscarCargoPorId, cadastrarCargo, atualizarCargo, excluirCargo...

    public Cargo buscarCargoPorId(int idCargo) {
        Cargo cargo = null;
        String sql = "SELECT c.id, c.nome, c.id_setor, s.nome AS nome_setor " +
                "FROM cargos c " +
                "JOIN setores s ON c.id_setor = s.id " +
                "WHERE c.id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCargo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cargo = new Cargo();
                    cargo.setId(rs.getInt("id"));
                    cargo.setNome(rs.getString("nome"));
                    cargo.setIdSetor(rs.getInt("id_setor"));
                    cargo.setNomeSetor(rs.getString("nome_setor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cargo;
    }

    public boolean cadastrarCargo(Cargo cargo) {
        String sql = "INSERT INTO cargos (nome, id_setor) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cargo.getNome());
            stmt.setInt(2, cargo.getIdSetor());
            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarCargo(Cargo cargo) {
        String sql = "UPDATE cargos SET nome = ?, id_setor = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cargo.getNome());
            stmt.setInt(2, cargo.getIdSetor());
            stmt.setInt(3, cargo.getId());
            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirCargo(int idCargo) {
        String sql = "DELETE FROM cargos WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCargo);
            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
