package com.api.servicodepagamento.model.response;

import com.api.servicodepagamento.model.util.FormaDePagamento;

public class DetalheFormaPagamento {

    private final String id;
    private final String descricao;

    public DetalheFormaPagamento(FormaDePagamento formaDePagamento) {
        this.id = formaDePagamento.name();
        this.descricao = formaDePagamento.getDescricao();
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
