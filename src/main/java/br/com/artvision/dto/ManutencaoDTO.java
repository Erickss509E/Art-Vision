package br.com.artvision.dto;

import java.time.LocalDate;

public class ManutencaoDTO {
    private int idManutencao;
    private String nomeManutencao;
    private LocalDate dataManutencao;
    private String observacao;
    private int idObra;
    private String nomeObra;
    private int idFunc;
    private String nomeFuncionario;
    private int idUsuario;
    private String status;

    public ManutencaoDTO(int idManutencao, String nomeManutencao, LocalDate dataManutencao, 
                        String observacao, int idObra, String nomeObra, int idFunc, 
                        String nomeFuncionario, int idUsuario, String status) {
        this.idManutencao = idManutencao;
        this.nomeManutencao = nomeManutencao;
        this.dataManutencao = dataManutencao;
        this.observacao = observacao;
        this.idObra = idObra;
        this.nomeObra = nomeObra;
        this.idFunc = idFunc;
        this.nomeFuncionario = nomeFuncionario;
        this.idUsuario = idUsuario;
        this.status = status;
    }

    public int getIdManutencao() { return idManutencao; }
    public void setIdManutencao(int idManutencao) { this.idManutencao = idManutencao; }

    public String getNomeManutencao() { return nomeManutencao; }
    public void setNomeManutencao(String nomeManutencao) { this.nomeManutencao = nomeManutencao; }

    public LocalDate getDataManutencao() { return dataManutencao; }
    public void setDataManutencao(LocalDate dataManutencao) { this.dataManutencao = dataManutencao; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public int getIdObra() { return idObra; }
    public void setIdObra(int idObra) { this.idObra = idObra; }

    public String getNomeObra() { return nomeObra; }
    public void setNomeObra(String nomeObra) { this.nomeObra = nomeObra; }

    public int getIdFunc() { return idFunc; }
    public void setIdFunc(int idFunc) { this.idFunc = idFunc; }

    public String getNomeFuncionario() { return nomeFuncionario; }
    public void setNomeFuncionario(String nomeFuncionario) { this.nomeFuncionario = nomeFuncionario; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
