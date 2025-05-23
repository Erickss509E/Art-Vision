package br.com.artvision.services;

import br.com.artvision.dao.DepartamentoDAO;
import br.com.artvision.dto.DepartamentoDTO;
import br.com.artvision.models.Departamento;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoService {
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();

    public List<DepartamentoDTO> listarDepartamentos() {
        System.out.println("DepartamentoService: Iniciando listagem de departamentos");
        List<Departamento> departamentos = departamentoDAO.listarDepartamentos();
        List<DepartamentoDTO> dtos = new ArrayList<>();

        System.out.println("DepartamentoService: Número de departamentos encontrados: " + (departamentos != null ? departamentos.size() : 0));

        if (departamentos != null) {
            for (Departamento departamento : departamentos) {
                dtos.add(new DepartamentoDTO(
                        departamento.getIdDepto(),
                        departamento.getNomeDepto(),
                        departamento.getIdSetor()
                ));
            }
        }
        return dtos;
    }

    public Departamento buscarDeptoPorId(int idDepto) {
        return departamentoDAO.buscarDepartamentoPorId(idDepto);
    }

    public boolean atualizarDepto(Departamento departamento) {
        return departamentoDAO.atualizarDepartamento(departamento);
    }

    public boolean cadastrarDepto(Departamento departamento) {
        return departamentoDAO.cadastrarDepartamento(departamento);
    }

    public boolean excluirDepto(int idDepto) {
        return departamentoDAO.excluirDepartamento(idDepto);
    }
}
