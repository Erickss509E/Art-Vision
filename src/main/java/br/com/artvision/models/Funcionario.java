package br.com.artvision.models;

public class Funcionario {
    private int idFunc;
    private String cpfFunc;
    private String nomeFunc;
    private String telefoneFunc;
    private String emailFunc;
    private int idCargo;
    private int idSetor;
    private int idEscala;
    private int idDepto;

    public int getIdFunc() {
        return idFunc;
    }
    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }
    public String getCpfFunc() {
        return cpfFunc;
    }
    public void setCpfFunc(String cpfFunc) {
        this.cpfFunc = cpfFunc;
    }
    public String getNomeFunc() {
        return nomeFunc;
    }
    public void setNomeFunc(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }
    public String getTelefoneFunc() {
        return telefoneFunc;
    }
    public void setTelefoneFunc(String telefoneFunc) {
        this.telefoneFunc = telefoneFunc;
    }
    public String getEmailFunc() {
        return emailFunc;
    }
    public void setEmailFunc(String emailFunc) {
        this.emailFunc = emailFunc;
    }
    public int getIdCargo() {
        return idCargo;
    }
    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }
    public int getIdSetor() {
        return idSetor;
    }
    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }
    public int getIdEscala() {
        return idEscala;
    }
    public void setIdEscala(int idEscala) {
        this.idEscala = idEscala;
    }
    public int getIdDepto() {
        return idDepto;
    }
    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }
}
