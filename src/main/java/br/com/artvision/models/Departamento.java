package br.com.artvision.models;

public class Departamento {
    private int idDepto;
    private String nomeDepto;
    private String idSetor;

    public Departamento(int idDepto, String nomeDepto, String idSetor) {
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

    public String getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(String idSetor) {
        this.idSetor = idSetor;
    }
}


