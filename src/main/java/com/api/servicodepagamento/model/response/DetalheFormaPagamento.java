package com.api.servicodepagamento.model.response;

import com.api.servicodepagamento.util.FormaDePagamento;

public class DetalheFormaPagamento {

    private String id;
    private String descricao;

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
