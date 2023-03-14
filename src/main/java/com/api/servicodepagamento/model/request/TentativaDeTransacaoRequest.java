package com.api.servicodepagamento.model.request;

import com.api.servicodepagamento.model.entities.Restaurante;
import com.api.servicodepagamento.model.entities.Pagamento;
import com.api.servicodepagamento.model.entities.Usuario;
import com.api.servicodepagamento.service.TentativaDeTransacaoService;
import com.api.servicodepagamento.service.annotations.ExistId;
import com.api.servicodepagamento.util.FormaDePagamento;
import com.api.servicodepagamento.util.StatusTrasacao;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TentativaDeTransacaoRequest {

    @NotNull
    private FormaDePagamento formaDePagamento;
    @NotNull
    @ExistId(className = Restaurante.class)
    private Long idRestaurante;
    @NotNull
    @ExistId(className = Usuario.class)
    private Long idUsuario;

    @Deprecated
    public TentativaDeTransacaoRequest() {
    }

    public TentativaDeTransacaoRequest(FormaDePagamento formaDePagamento,
                                       Long idRestaurante,
                                       Long idUsuario) {
        this.formaDePagamento = formaDePagamento;
        this.idRestaurante = idRestaurante;
        this.idUsuario = idUsuario;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean eOffiline() {
        return !formaDePagamento.getOnline();
    }

    public Pagamento toModel(TentativaDeTransacaoService service, Long idPedido, BigDecimal valor) {
        @NotNull Usuario usuario = service.find(Usuario.class, idUsuario);
        @NotNull Restaurante restaurante = service.find(Restaurante.class, idRestaurante);

        return new Pagamento(idPedido, valor, usuario, restaurante, StatusTrasacao.esperando_confirmacao_pagamento);
    }
}
