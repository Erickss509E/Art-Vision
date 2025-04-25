package br.com.artvision.model;

import java.util.Date;

public class Manutencao {
    private int idManutencao;
    private String nomeManutencao;
    private Date dataManutencao;
    private String observacao;
    private int idObra;
    private int idFunc;
    private int idUsuario;

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
    public Date getDataManutencao() {
        return dataManutencao;
    }
    public void setDataManutencao(Date dataManutencao) {
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
