package com.api.servicodepagamento.model.util;

public enum FormaDePagamento {

    master(true, "Cartao master"),
    visa(true, "Cartao visa"),
    elo(true, "Cartao elo"),
    hypercard(true, "Cartao hypercard"),
    dinheiro(false, "Informar o troco"),
    maquina(false, "Passar o cartão"),
    cheque(false, "Tem quer ser valido");

    private final String descricao;

    private FormaDePagamento(Boolean online, String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
