package br.com.artvision.models;

import java.util.Date;

public class Treinamento {
    private int idTreinamento;
    private int idFuncionario;
    private String nomeTreinamento;
    private Date dataRealizacao;
    private String qualificacao;

    public int getIdTreinamento() {
        return idTreinamento;
    }

    public void setIdTreinamento(int idTreinamento) {
        this.idTreinamento = idTreinamento;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeTreinamento() {
        return nomeTreinamento;
    }

    public void setNomeTreinamento(String nomeTreinamento) {
        this.nomeTreinamento = nomeTreinamento;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public String getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(String qualificacao) {
        this.qualificacao = qualificacao;
    }
}
