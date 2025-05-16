package br.com.artvision.dto;

public class UsuarioDTO {
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String empresa;

    public UsuarioDTO() {
    }

    public UsuarioDTO(int id, String nome, String email, String cpf, String empresa) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.empresa = empresa;
    }

    // Getters e Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
