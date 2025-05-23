package br.com.artvision.services;

import br.com.artvision.dao.ManutencaoDAO;
import br.com.artvision.dto.ManutencaoDTO;
import br.com.artvision.models.Manutencao;

import java.util.List;

public class ManutencaoService {

    private ManutencaoDAO manutencaoDAO = new ManutencaoDAO();

    public List<ManutencaoDTO> listarManu() {
        return manutencaoDAO.listarManu();
    }

    public Manutencao buscarPorId(int idManutencao) {
        return manutencaoDAO.buscarManutencaoPorId(idManutencao);
    }

    public boolean cadastrarManu(Manutencao manutencao) {
        return manutencaoDAO.cadastrarManu(manutencao);
    }

    public boolean atualizarManutencao(Manutencao manutencao) {
        return manutencaoDAO.atualizarManu(manutencao);
    }

    public boolean excluirManu(int idManutencao) {
        return manutencaoDAO.excluirManu(idManutencao);
    }
}
