package com.api.servicodepagamento.controller;

import com.api.servicodepagamento.model.entities.Pagamento;
import com.api.servicodepagamento.model.request.TentativaDeTransacaoRequest;
import com.api.servicodepagamento.service.ObterValorPedido;
import com.api.servicodepagamento.service.TentativaDeTransacaoService;
import com.api.servicodepagamento.service.validator.TentativaDeTrasacaoValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class TentativaDeTransacaoController {

    @Autowired
    private TentativaDeTransacaoService service;

    @Autowired
    private TentativaDeTrasacaoValidator tentativaDeTrasacaoValidator;

    @Autowired
    private ObterValorPedido obterValorPedido;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(tentativaDeTrasacaoValidator);
    }

    @PostMapping(value = "/pagamento/offline/{idPedido}")
    public ResponseEntity<String> tentativaPaga(@PathVariable Long idPedido,
                                                @RequestBody @Valid TentativaDeTransacaoRequest request) throws Exception {

        BigDecimal valor = obterValorPedido.executa(idPedido, () -> {
            BindException bindException = new BindException("", "");
            bindException.reject(null, "Esse id de pedido não existe");
            return bindException;
        });

        Pagamento novaPagamentoOffline = request.toModel(service, idPedido, valor);
        service.insert(novaPagamentoOffline);
        return ResponseEntity.ok().body(novaPagamentoOffline.getUuid());
    }

}
