package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.dto.ObraDTO;
import br.com.artvision.models.Obra;
import br.com.artvision.utils.CascadeDeleteUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObraDAO {
    public List<ObraDTO> listarObras() {
        List<ObraDTO> obras = new ArrayList<>();
        String sql = "SELECT o.*, s.nome_setor, f.nome_func, u.nome as nome_usuario " +
                    "FROM obras o " +
                    "LEFT JOIN setores s ON o.id_setor = s.id_setor " +
                    "LEFT JOIN funcionarios f ON o.id_func = f.id_func " +
                    "LEFT JOIN usuarios u ON o.id_usuario = u.id_usuario";

        try (Connection conn = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ObraDTO obra = new ObraDTO();
                obra.setId(rs.getInt("id_obra"));
                obra.setNomeObra(rs.getString("nome_obra"));
                obra.setNomeAutor(rs.getString("nome_autor"));
                obra.setDataEntrada(rs.getDate("data_entrada"));
                obra.setDataSaida(rs.getDate("data_saida"));
                obra.setValorEstimado(rs.getDouble("valor_estimado"));
                obra.setNomeSetor(rs.getString("nome_setor"));
                obra.setNomeFuncionario(rs.getString("nome_func"));
                obra.setNomeUsuario(rs.getString("nome_usuario"));
                obras.add(obra);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar obras: " + e.getMessage());
            e.printStackTrace();
        }

        return obras;
    }

    public Obra buscarObraPorId(int id) {
        Obra obra = null;
        String sql = "SELECT * FROM obras WHERE id_obra = ?";

        try (Connection conn = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    obra = new Obra();
                    obra.setId(rs.getInt("id_obra"));
                    obra.setNomeObra(rs.getString("nome_obra"));
                    obra.setNomeAutor(rs.getString("nome_autor"));
                    obra.setDataEntrada(rs.getDate("data_entrada"));
                    obra.setDataSaida(rs.getDate("data_saida"));
                    obra.setValorEstimado(rs.getDouble("valor_estimado"));
                    obra.setIdSetor(rs.getInt("id_setor"));
                    obra.setIdFunc(rs.getInt("id_func"));
                    obra.setIdUsuario(rs.getInt("id_usuario"));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar obra por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return obra;
    }

    public boolean cadastrarObra(Obra obra) {
        String sql = "INSERT INTO obras (nome_obra, nome_autor, data_entrada, data_saida, " +
                    "valor_estimado, id_setor, id_func, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionPoolConfig.getConnection()) {
            System.out.println("\n=== DEBUG: Iniciando cadastro de obra ===");
            System.out.println("Conexão com o banco estabelecida: " + (conn != null));
            
            // Validar dados antes de inserir
            if (obra.getNomeObra() == null || obra.getNomeObra().trim().isEmpty()) {
                System.out.println("Erro: Nome da obra não pode ser vazio");
                return false;
            }
            if (obra.getNomeAutor() == null || obra.getNomeAutor().trim().isEmpty()) {
                System.out.println("Erro: Nome do autor não pode ser vazio");
                return false;
            }
            if (obra.getDataEntrada() == null) {
                System.out.println("Erro: Data de entrada não pode ser nula");
                return false;
            }
            if (obra.getValorEstimado() <= 0) {
                System.out.println("Erro: Valor estimado deve ser maior que zero");
                return false;
            }

            System.out.println("\nDados da obra a ser cadastrada:");
            System.out.println("Nome: '" + obra.getNomeObra() + "'");
            System.out.println("Autor: '" + obra.getNomeAutor() + "'");
            System.out.println("Data Entrada: '" + obra.getDataEntrada() + "'");
            System.out.println("Data Saída: '" + obra.getDataSaida() + "'");
            System.out.println("Valor: '" + obra.getValorEstimado() + "'");
            System.out.println("ID Setor: '" + obra.getIdSetor() + "'");
            System.out.println("ID Func: '" + obra.getIdFunc() + "'");
            System.out.println("ID Usuario: '" + obra.getIdUsuario() + "'");

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                System.out.println("\nPreparando statement SQL...");
                
                stmt.setString(1, obra.getNomeObra());
                System.out.println("1. nome_obra definido");
                
                stmt.setString(2, obra.getNomeAutor());
                System.out.println("2. nome_autor definido");
                
                stmt.setDate(3, new java.sql.Date(obra.getDataEntrada().getTime()));
                System.out.println("3. data_entrada definida");
                
                stmt.setDate(4, obra.getDataSaida() != null ? new java.sql.Date(obra.getDataSaida().getTime()) : null);
                System.out.println("4. data_saida definida");
                
                stmt.setDouble(5, obra.getValorEstimado());
                System.out.println("5. valor_estimado definido");
                
                stmt.setInt(6, obra.getIdSetor());
                System.out.println("6. id_setor definido: " + obra.getIdSetor());
                
                stmt.setInt(7, obra.getIdFunc());
                System.out.println("7. id_func definido: " + obra.getIdFunc());
                
                stmt.setInt(8, obra.getIdUsuario());
                System.out.println("8. id_usuario definido: " + obra.getIdUsuario());

                System.out.println("\nSQL preparado: " + sql);
                System.out.println("Executando SQL...");
                
                int rowsAffected = stmt.executeUpdate();
                System.out.println("Resultado da execução: " + rowsAffected + " linhas afetadas");
                
                if (rowsAffected > 0) {
                    System.out.println("Obra cadastrada com sucesso!");
                } else {
                    System.out.println("Nenhuma linha foi inserida no banco.");
                }
                
                return rowsAffected > 0;
            } catch (SQLException e) {
                System.out.println("\nErro SQL ao executar insert:");
                System.out.println("Código do erro: " + e.getErrorCode());
                System.out.println("Estado SQL: " + e.getSQLState());
                System.out.println("Mensagem: " + e.getMessage());
                
                if (e.getMessage().contains("foreign key constraint")) {
                    System.out.println("\nErro de chave estrangeira detectado!");
                    System.out.println("Verifique se os seguintes IDs existem em suas respectivas tabelas:");
                    System.out.println("- ID do setor (" + obra.getIdSetor() + ") na tabela setores");
                    System.out.println("- ID do funcionário (" + obra.getIdFunc() + ") na tabela funcionarios");
                    System.out.println("- ID do usuário (" + obra.getIdUsuario() + ") na tabela usuarios");
                }
                
                e.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            System.out.println("\nErro geral ao tentar cadastrar obra:");
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarObra(Obra obra) {
        String sql = "UPDATE obras SET nome_obra = ?, nome_autor = ?, data_entrada = ?, " +
                    "data_saida = ?, valor_estimado = ?, id_setor = ?, id_func = ?, " +
                    "id_usuario = ? WHERE id_obra = ?";

        try (Connection conn = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, obra.getNomeObra());
            stmt.setString(2, obra.getNomeAutor());
            stmt.setDate(3, new java.sql.Date(obra.getDataEntrada().getTime()));
            stmt.setDate(4, obra.getDataSaida() != null ? new java.sql.Date(obra.getDataSaida().getTime()) : null);
            stmt.setDouble(5, obra.getValorEstimado());
            stmt.setInt(6, obra.getIdSetor());
            stmt.setInt(7, obra.getIdFunc());
            stmt.setInt(8, obra.getIdUsuario());
            stmt.setInt(9, obra.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar obra: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirObra(int id) {
        String sql = "DELETE FROM obras WHERE id_obra = ?";

        try (Connection conn = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao excluir obra: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean corrigirEncodingNome(int idObra, String novoNome) {
        String sql = "UPDATE obras SET nome_obra = ? WHERE id_obra = ?";

        try (Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setInt(2, idObra);
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar nome da obra: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}