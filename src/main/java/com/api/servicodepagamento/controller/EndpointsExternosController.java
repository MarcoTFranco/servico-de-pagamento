package com.api.servicodepagamento.controller;

import com.api.servicodepagamento.model.request.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class EndpointsExternosController {

    @GetMapping(value = "/api/pedidos/{idPedido}")
    public ResponseEntity<Pedido> pedido(@PathVariable Long idPedido) {
        Pedido pedido = new Pedido(idPedido, new BigDecimal("50.00"));
        return ResponseEntity.ok().body(pedido);
    }

}
