package com.api.servicodepagamento.model.request;

import java.math.BigDecimal;

public class Pedido {

    private Long id;
    private BigDecimal valor;

    @Deprecated
    public Pedido() {
    }

    public Pedido(Long id, BigDecimal valor) {
        this.id = id;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
