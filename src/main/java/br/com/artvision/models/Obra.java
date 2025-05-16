package br.com.artvision.models;

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

    public Obra(int id, String nome, String nomeAutor, Date dataEntradaMuseu, double valorEstimado, String localizacao, String descricao, Date ultimaManutencao, long tempoDesdeUltimaManutencao, String areaMuseu) {
        this.id = id;
        this.nome = nome;
        this.nomeAutor = nomeAutor;
        this.dataEntradaMuseu = dataEntradaMuseu;
        this.valorEstimado = valorEstimado;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.ultimaManutencao = ultimaManutencao;
        this.tempoDesdeUltimaManutencao = tempoDesdeUltimaManutencao;
        this.areaMuseu = areaMuseu;
    }

    public Obra(String nome, String descricao) {}

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

    public Date getDataEntradaMuseu() {
        return dataEntradaMuseu;
    }

    public void setDataEntradaMuseu(Date dataEntradaMuseu) {
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

    public Date getUltimaManutencao() {
        return ultimaManutencao;
    }

    public void setUltimaManutencao(Date ultimaManutencao) {
        this.ultimaManutencao = ultimaManutencao;
    }

    public long getTempoDesdeUltimaManutencao() {
        return tempoDesdeUltimaManutencao;
    }

    public void setTempoDesdeUltimaManutencao(long tempoDesdeUltimaManutencao) {
        this.tempoDesdeUltimaManutencao = tempoDesdeUltimaManutencao;
    }

    public String getAreaMuseu() {
        return areaMuseu;
    }

    public void setAreaMuseu(String areaMuseu) {
        this.areaMuseu = areaMuseu;
    }
}
