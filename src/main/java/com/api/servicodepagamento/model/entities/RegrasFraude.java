package com.api.servicodepagamento.model.entities;

import com.api.servicodepagamento.util.FormaDePagamento;

public interface RegrasFraude {

    boolean aceita(FormaDePagamento formaDePagamento, Usuario usuario);
}
