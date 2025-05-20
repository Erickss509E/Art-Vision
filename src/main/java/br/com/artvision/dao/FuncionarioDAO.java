package br.com.artvision.dao;
import br.com.artvision.database.ConnectionPoolConfig;

import br.com.artvision.models.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class    FuncionarioDAO {
    public static String COUNT_FUNC_SQL = "SELECT s.nome_setor, c.nome_cargo, COUNT(f.id_func) AS total " +
            "FROM funcionarios f " +
            "JOIN setores s ON f.id_setor = s.id_setor " +
            "JOIN cargos c ON f.id_cargo = c.id_cargo " +
            "GROUP BY s.nome_setor, c.nome_cargo";
    public static String INSERT_INTO_SQL = "INSERT INTO funcionarios (cpf_func, nome_func, telefone_func, email_func, id_cargo, id_setor, id_depto) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static String SELECT_FUNC_SQL = "SELECT * FROM funcionarios";
    public static String SELECT_BY_ID_SQL = "SELECT * FROM funcionarios WHERE id_func = ?";
    public static String UPDATE_FUNC_SQL = "UPDATE funcionarios SET cpf_func = ?, nome_func = ?, telefone_func = ?, email_func = ?, id_cargo = ?, id_setor = ?, id_depto = ? WHERE id_func = ?";
    public static String DELETE_FUNC_SQL = "DELETE FROM funcionarios WHERE id_func = ?";

    public boolean cadastrarFuncionario(Funcionario funcionario) {

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_INTO_SQL)) {

            stmt.setString(1, funcionario.getCpfFunc());
            stmt.setString(2, funcionario.getNomeFunc());
            stmt.setString(3, funcionario.getTelefoneFunc());
            stmt.setString(4, funcionario.getEmailFunc());
            stmt.setInt(5, funcionario.getIdCargo());
            stmt.setInt(6, funcionario.getIdSetor());
            stmt.setInt(7, funcionario.getIdDepto());
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_FUNC_SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario func = new Funcionario(
                        rs.getInt("id_func"),
                        rs.getString("nome_func"),
                        rs.getString("telefone_func"),
                        rs.getString("email_func"),
                        rs.getInt("id_cargo"),
                        rs.getInt("id_setor"),
                        rs.getInt("id_depto")
                        );
                funcionarios.add(func);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    public Funcionario buscarFuncionarioPorId(int id_func) {
        Funcionario funcionario = null;

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)){

            stmt.setInt(1, id_func);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    funcionario = new Funcionario(
                        rs.getInt("id_func"),
                        rs.getString("nome_func"),
                        rs.getString("telefone_func"),
                        rs.getString("email_func"),
                        rs.getInt("id_cargo"),
                        rs.getInt("id_setor"),
                        rs.getInt("id_depto")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return funcionario;
    }

    public boolean atualizarFuncionario(Funcionario funcionario) {

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_FUNC_SQL)) {

            stmt.setString(1, funcionario.getCpfFunc());
            stmt.setString(2, funcionario.getNomeFunc());
            stmt.setString(3, funcionario.getTelefoneFunc());
            stmt.setString(4, funcionario.getEmailFunc());
            stmt.setInt(5, funcionario.getIdCargo());
            stmt.setInt(6, funcionario.getIdSetor());
            stmt.setInt(7, funcionario.getIdDepto());
            stmt.setInt(8, funcionario.getIdFunc());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirFuncionario(int id_func) {

        try (Connection conn = ConnectionPoolConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_FUNC_SQL)) {

            stmt.setInt(1, id_func);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
