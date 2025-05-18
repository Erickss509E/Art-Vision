package br.com.artvision.services;

import br.com.artvision.dao.DepartamentoDAO;
import br.com.artvision.dao.FuncionarioDAO;
import br.com.artvision.dto.DepartamentoDTO;
import br.com.artvision.dto.FuncionarioDTO;
import br.com.artvision.models.Departamento;
import br.com.artvision.models.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoService {

    DepartamentoDAO DepartamentoDAO = new DepartamentoDAO();

    public List<DepartamentoDTO> listarDepartamentos() {
        List<Departamento> departamentos = DepartamentoDAO.listarDepartamentos();
        List<DepartamentoDTO> dtos = new ArrayList<>();

        for (Departamento departamento : departamentos) {
            DepartamentoDTO dto = new DepartamentoDTO(
                    departamento.getIdDepto(),
                    departamento.getNomeDepto(),
                    departamento.getIdSetor()
            );
            dtos.add(dto);
        }

        return dtos;
    }

    public Departamento buscarDeptoPorId(int id_depto) {
        return DepartamentoDAO.buscarDepartamentoPorId(id_depto);
    }

    public boolean atualizarDepto(Departamento departamento) {
        return DepartamentoDAO.atualizarDepartamento(departamento);
    }

    public boolean cadastrarDepto(Departamento departamento) {
        return DepartamentoDAO.cadastrarDepartamento(departamento);
    }

    public boolean excluirDepto(int id_depto) {
        return DepartamentoDAO.excluirDepartamento(id_depto);
    }

}
