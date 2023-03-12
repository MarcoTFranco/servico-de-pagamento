package com.api.servicodepagamento.model.request;

import com.api.servicodepagamento.model.entities.Restaurante;
import com.api.servicodepagamento.model.util.FormaDePagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class RestauranteRequest {
    @NotBlank
    private String nome;
    @Size(min = 1)
    private Set<FormaDePagamento> formasDePagamento = new HashSet<>();

    @Deprecated
    public RestauranteRequest() {
    }

    public RestauranteRequest(@NotBlank String nome,
                              @Size(min = 1) Set<FormaDePagamento> formasDePagamento) {
        this.nome = nome;
        this.formasDePagamento.addAll(formasDePagamento);
    }

    public String getNome() {
        return nome;
    }

    public Set<FormaDePagamento> getFormasDePagamento() {
        return formasDePagamento;
    }


    public Restaurante toModel() {
        return new Restaurante(nome, formasDePagamento);
    }
}
