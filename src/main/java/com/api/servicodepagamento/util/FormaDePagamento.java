package com.api.servicodepagamento.util;

public enum FormaDePagamento {

    master(true, "Cartao master"),
    visa(true, "Cartao visa"),
    elo(true, "Cartao elo"),
    hypercard(true, "Cartao hypercard"),
    dinheiro(false, "Informar o troco"),
    maquina(false, "Passar o cartão"),
    cheque(false, "Tem quer ser valido");

    private final String descricao;
    private final Boolean online;

    FormaDePagamento(Boolean online, String descricao) {
        this.online = online;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public Boolean getOnline() {
        return online;
    }
}
