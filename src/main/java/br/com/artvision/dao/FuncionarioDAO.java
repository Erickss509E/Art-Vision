package br.com.artvision.dao;

import br.com.artvision.database.ConnectionPoolConfig;
import br.com.artvision.dto.FuncionarioDTO;
import br.com.artvision.models.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    public List<FuncionarioDTO> listarFuncionarios() {
        List<FuncionarioDTO> funcionarios = new ArrayList<>();
        String sql = "SELECT f.id_func, f.nome_func, f.cpf_func, f.email_func, f.telefone_func, f.matricula_func, " +
                "c.nome_cargo, s.nome_setor, d.nome_depto " +
                "FROM funcionarios f " +
                "LEFT JOIN cargos c ON f.id_cargo = c.id_cargo " +
                "LEFT JOIN setores s ON f.id_setor = s.id_setor " +
                "LEFT JOIN departamentos d ON f.id_depto = d.id_depto";

        try (Connection conn = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FuncionarioDTO func = new FuncionarioDTO();
                func.setIdFunc(rs.getInt("id_func"));
                func.setNomeFunc(rs.getString("nome_func"));
                func.setCpfFunc(rs.getString("cpf_func"));
                func.setEmailFunc(rs.getString("email_func"));
                func.setTelefoneFunc(rs.getString("telefone_func"));
                func.setMatriculaFunc(rs.getString("matricula_func"));
                func.setNomeCargo(rs.getString("nome_cargo"));
                func.setNomeSetor(rs.getString("nome_setor"));
                func.setNomeDepto(rs.getString("nome_depto"));
                funcionarios.add(func);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        Funcionario funcionario = null;
        String sql = "SELECT * FROM funcionarios WHERE id_func = ?";

        try (Connection conn = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    funcionario = new Funcionario();
                    funcionario.setIdFunc(rs.getInt("id_func"));
                    funcionario.setNomeFunc(rs.getString("nome_func"));
                    funcionario.setCpfFunc(rs.getString("cpf_func"));
                    funcionario.setEmailFunc(rs.getString("email_func"));
                    funcionario.setTelefoneFunc(rs.getString("telefone_func"));
                    funcionario.setMatriculaFunc(rs.getString("matricula_func"));
                    funcionario.setIdCargo(rs.getInt("id_cargo"));
                    funcionario.setIdSetor(rs.getInt("id_setor"));
                    funcionario.setIdDepto(rs.getInt("id_depto"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return funcionario;
    }

    public boolean cadastrarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios (nome_func, cpf_func, email_func, telefone_func, matricula_func, id_cargo, id_setor, id_depto) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNomeFunc());
            stmt.setString(2, funcionario.getCpfFunc());
            stmt.setString(3, funcionario.getEmailFunc());
            stmt.setString(4, funcionario.getTelefoneFunc());
            stmt.setString(5, funcionario.getMatriculaFunc());
            stmt.setInt(6, funcionario.getIdCargo());
            stmt.setInt(7, funcionario.getIdSetor());
            stmt.setInt(8, funcionario.getIdDepto());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE funcionarios SET nome_func = ?, cpf_func = ?, email_func = ?, telefone_func = ?, " +
                "matricula_func = ?, id_cargo = ?, id_setor = ?, id_depto = ? WHERE id_func = ?";

        try (Connection conn = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNomeFunc());
            stmt.setString(2, funcionario.getCpfFunc());
            stmt.setString(3, funcionario.getEmailFunc());
            stmt.setString(4, funcionario.getTelefoneFunc());
            stmt.setString(5, funcionario.getMatriculaFunc());
            stmt.setInt(6, funcionario.getIdCargo());
            stmt.setInt(7, funcionario.getIdSetor());
            stmt.setInt(8, funcionario.getIdDepto());
            stmt.setInt(9, funcionario.getIdFunc());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirFuncionario(int idFunc) {
        String sql = "DELETE FROM funcionarios WHERE id_func = ?";

        try (Connection conn = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFunc);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}