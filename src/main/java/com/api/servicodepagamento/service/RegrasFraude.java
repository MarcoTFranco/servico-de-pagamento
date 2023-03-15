package com.api.servicodepagamento.service;

import com.api.servicodepagamento.model.entities.Usuario;
import com.api.servicodepagamento.util.FormaDePagamento;

public interface RegrasFraude {

    boolean aceita(FormaDePagamento formaDePagamento, Usuario usuario);
}
