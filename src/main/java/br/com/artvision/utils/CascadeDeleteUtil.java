package br.com.artvision.utils;

import br.com.artvision.database.ConnectionPoolConfig;
import java.sql.*;

public class CascadeDeleteUtil {
    
    public static boolean deleteFuncionario(Connection connection, int idFunc) throws SQLException {
        System.out.println("Iniciando exclusão do funcionário ID: " + idFunc);
        
        try {
            // First update obras to remove the funcionario reference
            try (PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE obras SET id_func = NULL WHERE id_func = ?")) {
                stmt.setInt(1, idFunc);
                int updatedObras = stmt.executeUpdate();
                System.out.println("Obras atualizadas: " + updatedObras);
            }

            // Then delete the funcionario
            try (PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM funcionarios WHERE id_func = ?")) {
                stmt.setInt(1, idFunc);
                int deleted = stmt.executeUpdate();
                System.out.println("Funcionário deletado: " + (deleted > 0));
                return deleted > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir funcionário: " + e.getMessage());
            throw e;
        }
    }

    public static boolean deleteCargo(Connection connection, int idCargo) throws SQLException {
        System.out.println("Iniciando exclusão do cargo ID: " + idCargo);
        
        try {
            // First update funcionarios that use this cargo
            try (PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE funcionarios SET id_cargo = NULL WHERE id_cargo = ?")) {
                stmt.setInt(1, idCargo);
                int updatedFuncs = stmt.executeUpdate();
                System.out.println("Funcionários atualizados: " + updatedFuncs);
            }

            // Then delete the cargo
            try (PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM cargos WHERE id_cargo = ?")) {
                stmt.setInt(1, idCargo);
                int deleted = stmt.executeUpdate();
                System.out.println("Cargo deletado: " + (deleted > 0));
                return deleted > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir cargo: " + e.getMessage());
            throw e;
        }
    }

    public static boolean deleteDepartamento(Connection connection, int idDepto) throws SQLException {
        System.out.println("Iniciando exclusão do departamento ID: " + idDepto);
        
        try {
            // First update funcionarios that use this departamento
            try (PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE funcionarios SET id_depto = NULL WHERE id_depto = ?")) {
                stmt.setInt(1, idDepto);
                int updatedFuncs = stmt.executeUpdate();
                System.out.println("Funcionários atualizados: " + updatedFuncs);
            }

            // Then delete the departamento
            try (PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM departamentos WHERE id_depto = ?")) {
                stmt.setInt(1, idDepto);
                int deleted = stmt.executeUpdate();
                System.out.println("Departamento deletado: " + (deleted > 0));
                return deleted > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir departamento: " + e.getMessage());
            throw e;
        }
    }

    public static boolean deleteSetor(Connection connection, int idSetor) throws SQLException {
        System.out.println("Iniciando exclusão do setor ID: " + idSetor);
        
        try {
            // 1. Update obras
            try (PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE obras SET id_setor = NULL WHERE id_setor = ?")) {
                stmt.setInt(1, idSetor);
                int updatedObras = stmt.executeUpdate();
                System.out.println("Obras atualizadas: " + updatedObras);
            }

            // 2. Update funcionarios
            try (PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE funcionarios SET id_setor = NULL WHERE id_setor = ?")) {
                stmt.setInt(1, idSetor);
                int updatedFuncs = stmt.executeUpdate();
                System.out.println("Funcionários atualizados: " + updatedFuncs);
            }

            // 3. Delete departamentos
            try (PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM departamentos WHERE id_setor = ?")) {
                stmt.setInt(1, idSetor);
                int deletedDeptos = stmt.executeUpdate();
                System.out.println("Departamentos deletados: " + deletedDeptos);
            }

            // 4. Delete cargos
            try (PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM cargos WHERE id_setor = ?")) {
                stmt.setInt(1, idSetor);
                int deletedCargos = stmt.executeUpdate();
                System.out.println("Cargos deletados: " + deletedCargos);
            }

            // 5. Finally delete the setor
            try (PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM setores WHERE id_setor = ?")) {
                stmt.setInt(1, idSetor);
                int deleted = stmt.executeUpdate();
                System.out.println("Setor deletado: " + (deleted > 0));
                return deleted > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir setor: " + e.getMessage());
            throw e;
        }
    }

    public static boolean deleteObra(Connection connection, int idObra) throws SQLException {
        System.out.println("Iniciando exclusão da obra ID: " + idObra);
        
        try {
            // Remove a obra
            try (PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM obras WHERE id_obra = ?")) {
                stmt.setInt(1, idObra);
                int deleted = stmt.executeUpdate();
                System.out.println("Obra deletada: " + (deleted > 0));
                return deleted > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir obra: " + e.getMessage());
            throw e;
        }
    }

    public static Connection iniciarTransacao() throws SQLException {
        Connection connection = ConnectionPoolConfig.getDataSource().getConnection();
        connection.setAutoCommit(false);
        System.out.println("Transação iniciada");
        return connection;
    }

    public static void finalizarTransacao(Connection connection, boolean sucesso) {
        try {
            if (connection != null) {
                if (sucesso) {
                    connection.commit();
                    System.out.println("Transação commitada com sucesso");
                } else {
                    connection.rollback();
                    System.out.println("Transação revertida");
                }
                connection.setAutoCommit(true);
                connection.close();
                System.out.println("Conexão fechada");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao finalizar transação: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 