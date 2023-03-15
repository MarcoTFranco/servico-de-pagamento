package com.api.servicodepagamento.service;

import com.api.servicodepagamento.model.request.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.function.Supplier;

@Component
public class ObterValorDoPedido {

    public BigDecimal executa(Long idPedido, Supplier<Exception> codigoEmCasoNaoPedidoExistente) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Pedido pedido = restTemplate
                    .getForObject("http://localhost:8080/api/pedidos/{idPedido}", Pedido.class, idPedido);
            return pedido.getValor();

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw codigoEmCasoNaoPedidoExistente.get();
            }
            throw e;
        }
    }

}
