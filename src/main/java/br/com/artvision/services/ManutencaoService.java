package br.com.artvision.services;

import br.com.artvision.dao.ManutencaoDAO;
import br.com.artvision.dto.ManutencaoDTO;
import br.com.artvision.models.Manutencao;

import java.util.ArrayList;
import java.util.List;

public class ManutencaoService {

    private ManutencaoDAO manutencaoDAO = new ManutencaoDAO();

    public List<ManutencaoDTO> listarManu() {
        List<Manutencao> manutencoes = manutencaoDAO.listarManu();
        List<ManutencaoDTO> dtos = new ArrayList<>();

        for (Manutencao manutencao : manutencoes) {
            ManutencaoDTO dto = new ManutencaoDTO(
                    manutencao.getIdManutencao(),
                    manutencao.getNomeManutencao(),
                    manutencao.getDataManutencao(),
                    manutencao.getObservacao(),
                    manutencao.getIdObra(),
                    manutencao.getIdFunc(),
                    manutencao.getIdUsuario()
            );
            dtos.add(dto);
        }

        return dtos;
    }

    public Manutencao buscarPorObra(int idObra) {
        return manutencaoDAO.buscarManutencaoPorId(idObra);
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
