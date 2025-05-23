package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.models.Setor;
import br.com.artvision.utils.CascadeDeleteUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SetorDAO {

    public boolean cadastrarSetor(Setor setor) {
        String sql = "INSERT INTO setores (nome_setor, ala) VALUES (?, ?)";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, setor.getNome());
            stmt.setString(2, setor.getAla());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar setor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Setor> listarSetor() {
        List<Setor> setores = new ArrayList<>();
        String sql = "SELECT id_setor, nome_setor, ala FROM setores";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Setor setor = new Setor();
                setor.setId(rs.getInt("id_setor"));
                setor.setNome(rs.getString("nome_setor"));
                setor.setAla(rs.getString("ala"));
                setores.add(setor);
                System.out.println("Setor encontrado: ID=" + setor.getId() + ", Nome=" + setor.getNome());
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar setores: " + e.getMessage());
            e.printStackTrace();
        }

        return setores;
    }

    public Setor buscarSetorPorId(int id_setor) {
        Setor setor = null;
        String sql = "SELECT id_setor, nome_setor, ala FROM setores WHERE id_setor = ?";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id_setor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    setor = new Setor(
                            rs.getString("nome_setor"),
                            rs.getString("ala")
                    );
                    setor.setId(rs.getInt("id_setor"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return setor;
    }

    public boolean atualizarSetor(Setor setor) {
        String sql = "UPDATE setores SET nome_setor = ?, ala = ? WHERE id_setor = ?";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, setor.getNome());
            stmt.setString(2, setor.getAla());
            stmt.setInt(3, setor.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean temDependencias(int id_setor) {
        String sql = "SELECT COUNT(*) as total FROM (" +
                    "SELECT id_setor FROM cargos WHERE id_setor = ? UNION ALL " +
                    "SELECT id_setor FROM departamentos WHERE id_setor = ? UNION ALL " +
                    "SELECT id_setor FROM obras WHERE id_setor = ?) as deps";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id_setor);
            stmt.setInt(2, id_setor);
            stmt.setInt(3, id_setor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true; // Em caso de erro, assume que tem dependências por segurança
    }

    public String listarDependencias(int id_setor) {
        StringBuilder deps = new StringBuilder();
        
        // Verificar cargos
        String sqlCargos = "SELECT nome_cargo FROM cargos WHERE id_setor = ?";
        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sqlCargos)) {
            stmt.setInt(1, id_setor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    deps.append("Cargo: ").append(rs.getString("nome_cargo")).append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Verificar departamentos
        String sqlDeps = "SELECT nome_depto FROM departamentos WHERE id_setor = ?";
        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sqlDeps)) {
            stmt.setInt(1, id_setor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    deps.append("Departamento: ").append(rs.getString("nome_depto")).append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Verificar obras
        String sqlObras = "SELECT nome_obra FROM obras WHERE id_setor = ?";
        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sqlObras)) {
            stmt.setInt(1, id_setor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    deps.append("Obra: ").append(rs.getString("nome_obra")).append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deps.toString();
    }

    public boolean excluirSetor(int id_setor) {
        Connection connection = null;
        try {
            System.out.println("Iniciando exclusão do setor ID: " + id_setor);
            
            // Verificar se existem dependências
            String deps = listarDependencias(id_setor);
            if (!deps.isEmpty()) {
                System.out.println("Dependências encontradas:\n" + deps);
            }
            
            connection = CascadeDeleteUtil.iniciarTransacao();
            boolean sucesso = CascadeDeleteUtil.deleteSetor(connection, id_setor);
            CascadeDeleteUtil.finalizarTransacao(connection, sucesso);
            
            if (sucesso) {
                System.out.println("Setor excluído com sucesso");
            } else {
                System.out.println("Nenhum setor foi excluído");
            }
            
            return sucesso;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir setor: " + e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                CascadeDeleteUtil.finalizarTransacao(connection, false);
            }
            return false;
        }
    }
}
