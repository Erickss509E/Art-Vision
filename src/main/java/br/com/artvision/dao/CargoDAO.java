package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Cargo;
import br.com.artvision.utils.CascadeDeleteUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {

    public List<Cargo> listarCargosComNomeSetor() {
        List<Cargo> lista = new ArrayList<>();

        String sql = "SELECT c.id_cargo, c.nome_cargo, c.id_setor, s.nome_setor " +
                "FROM cargos c " +
                "LEFT JOIN setores s ON c.id_setor = s.id_setor";

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id_cargo"));
                cargo.setNome(rs.getString("nome_cargo"));
                cargo.setIdSetor(rs.getInt("id_setor"));
                cargo.setNomeSetor(rs.getString("nome_setor"));
                lista.add(cargo);
                System.out.println("Cargo encontrado: ID=" + cargo.getId() + ", Nome=" + cargo.getNome() + ", Setor=" + cargo.getNomeSetor());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar cargos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public Cargo buscarCargoPorId(int idCargo) {
        Cargo cargo = null;
        String sql = "SELECT c.id_cargo, c.nome_cargo, c.id_setor, s.nome_setor AS nome_setor " +
                "FROM cargos c " +
                "JOIN setores s ON c.id_setor = s.id_setor " +
                "WHERE c.id_cargo = ?";

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCargo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cargo = new Cargo();
                    cargo.setId(rs.getInt("id_cargo"));
                    cargo.setNome(rs.getString("nome_cargo"));
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
        String sql = "INSERT INTO cargos (nome_cargo, id_setor) VALUES (?, ?)";
        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
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
        String sql = "UPDATE cargos SET nome_cargo = ?, id_setor = ? WHERE id_cargo = ?";
        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
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
        Connection connection = null;
        try {
            connection = CascadeDeleteUtil.iniciarTransacao();
            boolean sucesso = CascadeDeleteUtil.deleteCargo(connection, idCargo);
            CascadeDeleteUtil.finalizarTransacao(connection, sucesso);
            return sucesso;
        } catch (SQLException e) {
            if (connection != null) {
                CascadeDeleteUtil.finalizarTransacao(connection, false);
            }
            System.out.println("Erro ao excluir cargo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
