package br.com.artvision.models;

public class Departamento {
    private int idDepto;
    private String nomeDepto;
    private int idSetor;

    public Departamento() {}

    public Departamento(int idDepto, String nomeDepto, int idSetor) {
        this.idDepto = idDepto;
        this.nomeDepto = nomeDepto;
        this.idSetor = idSetor;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    public String getNomeDepto() {
        return nomeDepto;
    }

    public void setNomeDepto(String nomeDepto) {
        this.nomeDepto = nomeDepto;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }
}
