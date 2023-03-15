package com.api.servicodepagamento.controller;

import com.api.servicodepagamento.model.entities.Pagamento;
import com.api.servicodepagamento.model.request.PedidoOfflineRequest;
import com.api.servicodepagamento.service.ObterValorDoPedido;
import com.api.servicodepagamento.service.PagamentoOfflineService;
import com.api.servicodepagamento.service.validator.TentativaDeTrasacaoValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class PagamentoOfflineController {

    @Autowired
    private PagamentoOfflineService service;

    @Autowired
    private TentativaDeTrasacaoValidator tentativaDeTrasacaoValidator;

    @Autowired
    private ObterValorDoPedido obterValorDoPedido;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(tentativaDeTrasacaoValidator);
    }

    @PostMapping(value = "/pagamento/offline/{idPedido}")
    public ResponseEntity<String> tentativaPagamento(@PathVariable Long idPedido,
                                                     @RequestBody @Valid PedidoOfflineRequest request) throws Exception {

        BigDecimal valor = obterValorDoPedido.executa(idPedido, () -> {
            BindException bindException = new BindException("", "");
            bindException.reject(null, "Esse id de pedido não existe");
            return bindException;
        });

        Pagamento novaPagamentoOffline = request.toModel(service, idPedido, valor);
        service.persist(novaPagamentoOffline);
        return ResponseEntity.ok().body(novaPagamentoOffline.getUuid());
    }

    @PostMapping(value = "/pagamento/offline/{codigoPagamento}/concluir")
    public void concluiPagamento(@PathVariable String codigoPagamento) {

        Optional<Pagamento> possivelPagamento = service.findByCode(codigoPagamento);

        if (possivelPagamento.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Pagamento pagamento = possivelPagamento.get();

        if (pagamento.foiConcluido()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        pagamento.conclui();
        service.merge(pagamento);

    }
}
