package br.com.artvision.dto;

public class DepartamentoDTO {
    private int idDepto;
    private String nomeDepto;
    private int idSetor;

    public DepartamentoDTO(int idDepto, String nomeDepto, int idSetor) {
        this.idDepto = idDepto;
        this.nomeDepto = nomeDepto;
        this.idSetor = idSetor;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public String getNomeDepto() {
        return nomeDepto;
    }

    public int getIdSetor() {
        return idSetor;
    }
}
