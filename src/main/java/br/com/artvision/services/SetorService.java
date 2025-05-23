package br.com.artvision.services;

import br.com.artvision.dao.SetorDAO;
import br.com.artvision.dto.SetorDTO;
import br.com.artvision.models.Setor;

import java.util.ArrayList;
import java.util.List;

public class SetorService {

    private SetorDAO setorDAO = new SetorDAO();

    public List<SetorDTO> listarSetor() {
        List<Setor> setores = setorDAO.listarSetor();
        List<SetorDTO> dtos = new ArrayList<>();

        for (Setor setor : setores) {
            dtos.add(new SetorDTO(
                    setor.getId(),
                    setor.getNome(),
                    setor.getAla()
            ));
        }

        return dtos;
    }

    public Setor buscarSetoresPorId(int id) {
        return setorDAO.buscarSetorPorId(id);
    }

    public boolean atualizarSetores(Setor setor) {
        return setorDAO.atualizarSetor(setor);
    }

    public boolean cadastrarSetor(Setor setor) {
        return setorDAO.cadastrarSetor(setor);
    }

    public boolean excluirSetor(int id) {
        return setorDAO.excluirSetor(id);
    }
}
