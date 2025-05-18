package br.com.artvision.services;

import br.com.artvision.dao.CargoDAO;
import br.com.artvision.dao.SetorDAO;
import br.com.artvision.dto.CargoDTO;
import br.com.artvision.dto.SetorDTO;
import br.com.artvision.models.Cargo;
import br.com.artvision.models.Setor;

import java.util.ArrayList;
import java.util.List;

public class SetorService {

    SetorDAO SetorDAO = new SetorDAO();

    public List<SetorDTO> listarSetor() {
        List<Setor> setores = SetorDAO.listarSetor();
        List<SetorDTO> dtos = new ArrayList<>();

        for (Setor setor : setores) {
            SetorDTO dto = new SetorDTO(
                    setor.getId(),
                    setor.getNome(),
                    setor.getAla()
            );
            dtos.add(dto);
        }

        return dtos;
    }

    public Setor buscarSetoresPorId(int id) {
        return SetorDAO.buscarSetorPorId(id);
    }

    public boolean atualizarSetores(Setor setor) {
        return SetorDAO.atualizarSetor(setor);
    }

    public boolean cadastrarSetor(Setor setor) {
        return SetorDAO.cadastrarSetor(setor);
    }

    public boolean excluirSetor(int id) {
        return SetorDAO.excluirSetor(id);
    }
}
