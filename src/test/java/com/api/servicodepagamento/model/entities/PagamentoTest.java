package com.api.servicodepagamento.model.entities;

import com.api.servicodepagamento.util.FormaDePagamento;
import com.api.servicodepagamento.util.StatusTrasacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

class PagamentoTest {

    private final Pagamento pagamento = new Pagamento(1L, BigDecimal.TEN,
            new Usuario("teste@gmail.com.br", Collections.singleton(FormaDePagamento.dinheiro)),
            new Restaurante("teste", Collections.singleton(FormaDePagamento.dinheiro)),
            StatusTrasacao.esperando_confirmacao_pagamento);


    @Test
    @DisplayName("verificar que um pagamento nao foi concluido")
    void test1() {
        Assertions.assertFalse(pagamento.foiConcluido());
    }

    @Test
    @DisplayName("verificar que um pagamento foi concluido")
    void test2() {
        pagamento.conclui();
        Assertions.assertTrue(pagamento.foiConcluido());
    }

    @Test
    @DisplayName("verificar se pode concluir uma compra mais de uma vez")
    void test3() {
        pagamento.conclui();
        Assertions.assertThrows(IllegalArgumentException.class, pagamento::conclui);
    }
}