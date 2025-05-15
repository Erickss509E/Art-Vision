package br.com.artvision.dao;

import br.com.artvision.model.FuncionarioCount;
import br.com.artvision.model.Funcionario;
import br.com.artvision.servlet.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class    FuncionarioDAO {

    public List<FuncionarioCount> contarFuncionariosPorSetorECargo() {
        List<FuncionarioCount> counts = new ArrayList<>();
        String sql = "SELECT s.nome_setor, c.nome_cargo, COUNT(f.id_func) AS total " +
                     "FROM funcionarios f " +
                     "JOIN setores s ON f.id_setor = s.id_setor " +
                     "JOIN cargos c ON f.id_cargo = c.id_cargo " +
                     "GROUP BY s.nome_setor, c.nome_cargo";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FuncionarioCount fc = new FuncionarioCount();
                fc.setNomeSetor(rs.getString("nome_setor"));
                fc.setNomeCargo(rs.getString("nome_cargo"));
                fc.setTotal(rs.getInt("total"));
                counts.add(fc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return counts;
    }

    private Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        return factory.getConnection();
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios (cpf_func, nome_func, telefone_func, email_func, id_cargo, id_setor, id_escala, id_depto, id_nivel_acesso) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getCpfFunc());
            stmt.setString(2, funcionario.getNomeFunc());
            stmt.setString(3, funcionario.getTelefoneFunc());
            stmt.setString(4, funcionario.getEmailFunc());
            stmt.setInt(5, funcionario.getIdCargo());
            stmt.setInt(6, funcionario.getIdSetor());
            stmt.setInt(7, funcionario.getIdEscala());
            stmt.setInt(8, funcionario.getIdDepto());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setIdFunc(rs.getInt("id_func"));
                f.setCpfFunc(rs.getString("cpf_func"));
                f.setNomeFunc(rs.getString("nome_func"));
                f.setTelefoneFunc(rs.getString("telefone_func"));
                f.setEmailFunc(rs.getString("email_func"));
                f.setIdCargo(rs.getInt("id_cargo"));
                f.setIdSetor(rs.getInt("id_setor"));
                f.setIdEscala(rs.getInt("id_escala"));
                f.setIdDepto(rs.getInt("id_depto"));
                funcionarios.add(f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE funcionarios SET cpf_func = ?, nome_func = ?, telefone_func = ?, email_func = ?, id_cargo = ?, id_setor = ?, id_escala = ?, id_depto = ?, id_nivel_acesso = ? WHERE id_func = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getCpfFunc());
            stmt.setString(2, funcionario.getNomeFunc());
            stmt.setString(3, funcionario.getTelefoneFunc());
            stmt.setString(4, funcionario.getEmailFunc());
            stmt.setInt(5, funcionario.getIdCargo());
            stmt.setInt(6, funcionario.getIdSetor());
            stmt.setInt(7, funcionario.getIdEscala());
            stmt.setInt(8, funcionario.getIdDepto());
            stmt.setInt(10, funcionario.getIdFunc());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluirFuncionario(int idFunc) {
        String sql = "DELETE FROM funcionarios WHERE id_func = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFunc);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
