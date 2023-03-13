package com.api.servicodepagamento.model.entities;

import com.api.servicodepagamento.util.FormaDePagamento;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RegraDoPossivelFraudador implements RegrasFraude {

    private final Set<String> emailsFraudadores = Set.of("fraudador@gmail.com", "fraudador1@gmail.com");

    @Override
    public boolean aceita(FormaDePagamento formaDePagamento, Usuario usuario) {

        if (!formaDePagamento.getOnline()) {
            return true;
        }

        return !emailsFraudadores.contains(usuario.getEmail());
    }
}
