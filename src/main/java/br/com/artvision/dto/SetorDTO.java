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

    public String getNome() {
        return nome;
    }

    public String getAla() {
        return ala;
    }
}
