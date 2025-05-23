package br.com.artvision.services;

import br.com.artvision.dao.FuncionarioDAO;
import br.com.artvision.dto.FuncionarioDTO;
import br.com.artvision.dto.UsuarioDTO;
import br.com.artvision.models.Funcionario;
import br.com.artvision.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {

    FuncionarioDAO FuncionarioDAO = new FuncionarioDAO();

    public List<FuncionarioDTO> listarFuncionarios() {
        return FuncionarioDAO.listarFuncionarios();
    }

    public boolean cadastrarFuncionario(Funcionario funcionario) {
        return FuncionarioDAO.cadastrarFuncionario(funcionario);
    }

    public boolean atualizarFuncionario (Funcionario funcionario) {
        return FuncionarioDAO.atualizarFuncionario(funcionario);
    }

    public Funcionario buscarFuncionarioPorId (int id) {
        return FuncionarioDAO.buscarFuncionarioPorId(id);
    }

    public boolean excluirFuncionario (int id) {
        return FuncionarioDAO.excluirFuncionario(id);
    }

}
