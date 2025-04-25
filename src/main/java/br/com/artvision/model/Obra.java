package br.com.artvision.model;

import java.util.Date;

public class Obra {
    private int id;
    private String nome;
    private String nomeAutor;
    private Date dataEntradaMuseu;
    private double valorEstimado;
    private String localizacao;
    private String descricao;
    private java.util.Date ultimaManutencao;
    private long tempoDesdeUltimaManutencao; 
    private String areaMuseu;

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
    public String getNomeAutor() {
        return nomeAutor;
    }
    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }
    public java.util.Date getDataEntradaMuseu() {
        return dataEntradaMuseu;
    }
    public void setDataEntradaMuseu(java.util.Date dataEntradaMuseu) {
        this.dataEntradaMuseu = dataEntradaMuseu;
    }
    public double getValorEstimado() {
        return valorEstimado;
    }
    public void setValorEstimado(double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }
    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public java.util.Date getUltimaManutencao() {
        return ultimaManutencao;
    }

    public void setUltimaManutencao(java.util.Date ultimaManutencao) {
        this.ultimaManutencao = ultimaManutencao;
    }

    public long getTempoDesdeUltimaManutencao() {
        return tempoDesdeUltimaManutencao;
    }

    public void setTempoDesdeUltimaManutencao(long tempoDesdeUltimaManutencao) {
        this.tempoDesdeUltimaManutencao = tempoDesdeUltimaManutencao;
    }
}
