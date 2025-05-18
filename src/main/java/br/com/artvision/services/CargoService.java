package br.com.artvision.services;

import br.com.artvision.dao.CargoDAO;
import br.com.artvision.dao.DepartamentoDAO;
import br.com.artvision.dto.CargoDTO;
import br.com.artvision.dto.DepartamentoDTO;
import br.com.artvision.models.Cargo;
import br.com.artvision.models.Departamento;

import java.util.ArrayList;
import java.util.List;

public class CargoService {

    CargoDAO CargoDAO = new CargoDAO();

    public List<CargoDTO> listarCargos() {
        List<Cargo> cargos = CargoDAO.listarCargos();
        List<CargoDTO> dtos = new ArrayList<>();

        for (Cargo cargo : cargos) {
            CargoDTO dto = new CargoDTO(
                    cargo.getId(),
                    cargo.getNome(),
                    cargo.getIdSetor()
            );
            dtos.add(dto);
        }

        return dtos;
    }

    public Cargo buscarCargosPorId(int id_cargo) {
        return CargoDAO.buscarCargosPorId(id_cargo);
    }

    public boolean atualizarCargos(Cargo cargo) {
        return CargoDAO.atualizarCargo(cargo);
    }

    public boolean cadastrarCargos(Cargo cargo) {
        return CargoDAO.cadastrarCargo(cargo);
    }

    public boolean excluirCargo(int id_cargo) {
        return CargoDAO.excluirCargo(id_cargo);
    }

}
