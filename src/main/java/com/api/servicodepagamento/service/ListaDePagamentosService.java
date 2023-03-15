package com.api.servicodepagamento.service;

import com.api.servicodepagamento.model.entities.Restaurante;
import com.api.servicodepagamento.model.entities.Usuario;
import com.api.servicodepagamento.model.response.DetalheFormaPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListaDePagamentosService {

    @Autowired
    private Collection<RegrasFraude> regrasFraude;

    public List<DetalheFormaPagamento> detalheFormaPagamentos(Usuario usuario, Restaurante restaurante) {
        return usuario.listaDePagamentosAceitos(restaurante, regrasFraude)
                .stream()
                .map(DetalheFormaPagamento::new)
                .collect(Collectors.toList());
    }
}
