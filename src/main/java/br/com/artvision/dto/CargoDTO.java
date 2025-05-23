package br.com.artvision.dto;

public class CargoDTO {
    private int id;
    private String nome;
    private int idSetor;
    private String nomeSetor;

    public CargoDTO(int id, String nome, int idSetor, String nomeSetor) {
        this.id = id;
        this.nome = nome;
        this.idSetor = idSetor;
        this.nomeSetor = nomeSetor;
    }

    // Getters e setters

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

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }
}
