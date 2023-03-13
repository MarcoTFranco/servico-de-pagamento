package com.api.servicodepagamento.model.request;

import jakarta.validation.constraints.NotNull;

public class ListaFormasDePagamentoRequest {

    @NotNull
    private Long idUsuario;
    @NotNull
    private Long idRestaurante;

    @Deprecated
    public ListaFormasDePagamentoRequest() {
    }

    public ListaFormasDePagamentoRequest(@NotNull Long idUsuario,
                                         @NotNull Long idRestaurante) {
        this.idUsuario = idUsuario;
        this.idRestaurante = idRestaurante;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
}
