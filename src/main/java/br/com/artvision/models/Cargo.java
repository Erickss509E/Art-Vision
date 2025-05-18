// Cargo.java
package br.com.artvision.models;

public class Cargo {
    private int id;
    private String nome;
    private int idSetor;

    public Cargo(int idCargo, String nomeCargo, int idSetor) {
    }

    public Cargo() {

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

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }
}
