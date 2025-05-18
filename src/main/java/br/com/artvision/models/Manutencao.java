package br.com.artvision.models;


import java.time.LocalDate;

public class Manutencao {
    private int idManutencao;
    private String nomeManutencao;
    private LocalDate dataManutencao;
    private String observacao;
    private int idObra;
    private int idFunc;
    private int idUsuario;

    public Manutencao(int idManutencao, String nomeManutencao, LocalDate dataManutencao,
                      String observacao, int idObra, int idFunc, int idUsuario) {
        this.idManutencao = idManutencao;
        this.nomeManutencao = nomeManutencao;
        this.dataManutencao = dataManutencao;
        this.observacao = observacao;
        this.idObra = idObra;
        this.idFunc = idFunc;
        this.idUsuario = idUsuario;
    }

    public Manutencao() {

    }

    public int getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(int idManutencao) {
        this.idManutencao = idManutencao;
    }

    public String getNomeManutencao() {
        return nomeManutencao;
    }

    public void setNomeManutencao(String nomeManutencao) {
        this.nomeManutencao = nomeManutencao;
    }

    public LocalDate getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(LocalDate dataManutencao) {
        this.dataManutencao = dataManutencao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
