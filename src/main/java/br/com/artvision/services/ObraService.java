package br.com.artvision.services;

import br.com.artvision.dao.ObraDAO;
import br.com.artvision.dto.ObraDTO;
import br.com.artvision.models.Obra;

import java.util.List;

public class ObraService {
    private ObraDAO obraDAO;

    public ObraService() {
        this.obraDAO = new ObraDAO();
    }

    public List<ObraDTO> listarObras() {
        System.out.println("\n=== DEBUG: ObraService.listarObras() ===");
        return obraDAO.listarObras();
    }

    public Obra buscarObraPorId(int id) {
        System.out.println("\n=== DEBUG: ObraService.buscarObraPorId(" + id + ") ===");
        return obraDAO.buscarObraPorId(id);
    }

    public boolean cadastrarObra(Obra obra) {
        System.out.println("\n=== DEBUG: ObraService.cadastrarObra() ===");
        
        // Validações de negócio
        if (obra == null) {
            System.out.println("Erro: Objeto obra é nulo");
            return false;
        }

        if (obra.getNomeObra() == null || obra.getNomeObra().trim().isEmpty()) {
            System.out.println("Erro: Nome da obra é obrigatório");
            return false;
        }

        if (obra.getNomeAutor() == null || obra.getNomeAutor().trim().isEmpty()) {
            System.out.println("Erro: Nome do autor é obrigatório");
            return false;
        }

        if (obra.getDataEntrada() == null) {
            System.out.println("Erro: Data de entrada é obrigatória");
            return false;
        }

        if (obra.getValorEstimado() <= 0) {
            System.out.println("Erro: Valor estimado deve ser maior que zero");
            return false;
        }

        System.out.println("Todas as validações passaram, chamando obraDAO.cadastrarObra()");
        return obraDAO.cadastrarObra(obra);
    }

    public boolean atualizarObra(Obra obra) {
        System.out.println("\n=== DEBUG: ObraService.atualizarObra() ===");
        return obraDAO.atualizarObra(obra);
    }

    public boolean excluirObra(int id) {
        System.out.println("\n=== DEBUG: ObraService.excluirObra(" + id + ") ===");
        return obraDAO.excluirObra(id);
    }
} 