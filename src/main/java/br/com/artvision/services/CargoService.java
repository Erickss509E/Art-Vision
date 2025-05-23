package br.com.artvision.services;

import br.com.artvision.dao.CargoDAO;
import br.com.artvision.dto.CargoDTO;
import br.com.artvision.models.Cargo;

import java.util.ArrayList;
import java.util.List;

public class CargoService {

    private CargoDAO cargoDAO = new CargoDAO();

    public List<CargoDTO> listarCargos() {
        System.out.println("CargoService: Iniciando listagem de cargos");
        List<Cargo> cargos = cargoDAO.listarCargosComNomeSetor();
        List<CargoDTO> listaDTO = new ArrayList<>();

        System.out.println("CargoService: Número de cargos encontrados: " + (cargos != null ? cargos.size() : 0));

        if (cargos != null) {
            for (Cargo c : cargos) {
                listaDTO.add(new CargoDTO(c.getId(), c.getNome(), c.getIdSetor(), c.getNomeSetor()));
            }
        }

        return listaDTO;
    }

    public Cargo buscarCargoPorId(int idCargo) {
        return cargoDAO.buscarCargoPorId(idCargo);
    }

    public boolean atualizarCargo(Cargo cargo) {
        return cargoDAO.atualizarCargo(cargo);
    }

    public boolean cadastrarCargo(Cargo cargo) {
        return cargoDAO.cadastrarCargo(cargo);
    }

    public boolean excluirCargo(int idCargo) {
        return cargoDAO.excluirCargo(idCargo);
    }
}
