package br.com.artvision.model;

import java.util.Date;

public class EstadoConservacao {
    private int id;
    private int obraId;
    private String descricao;
    private String imagens; 
    private String historicoManutencao;
    private Date dataProximaManutencao;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getObraId() {
        return obraId;
    }
    public void setObraId(int obraId) {
        this.obraId = obraId;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getImagens() {
        return imagens;
    }
    public void setImagens(String imagens) {
        this.imagens = imagens;
    }
    public String getHistoricoManutencao() {
        return historicoManutencao;
    }
    public void setHistoricoManutencao(String historicoManutencao) {
        this.historicoManutencao = historicoManutencao;
    }
    public Date getDataProximaManutencao() {
        return dataProximaManutencao;
    }
    public void setDataProximaManutencao(Date dataProximaManutencao) {
        this.dataProximaManutencao = dataProximaManutencao;
    }
}
