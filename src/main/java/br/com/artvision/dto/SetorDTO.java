package br.com.artvision.dto;

public class SetorDTO {
    private int id;
    private String nome;
    private String ala;

    public SetorDTO(int id, String nome, String ala) {
        this.id = id;
        this.nome = nome;
        this.ala = ala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAla() {
        return ala;
    }

    public void setAla(String ala) {
        this.ala = ala;
    }
}
