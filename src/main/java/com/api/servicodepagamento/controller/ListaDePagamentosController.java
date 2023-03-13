package com.api.servicodepagamento.controller;

import com.api.servicodepagamento.model.entities.Restaurante;
import com.api.servicodepagamento.model.entities.Usuario;
import com.api.servicodepagamento.model.request.ListaFormasDePagamentoRequest;
import com.api.servicodepagamento.model.response.DetalheFormaPagamento;
import com.api.servicodepagamento.service.RestauranteService;
import com.api.servicodepagamento.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
public class ListaDePagamentosController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping(value = "/listas-pagamentos")
    public ResponseEntity<List<DetalheFormaPagamento>> pagamento(@Valid ListaFormasDePagamentoRequest request) {
        Usuario usuario = usuarioService.findById(request.getIdUsuario());
        Restaurante restaurante = restauranteService.findById(request.getIdRestaurante());

        List<DetalheFormaPagamento> detalheFormaPagamentos = usuario.listaDePagamentosAceitos(restaurante)
                .stream()
                .map(DetalheFormaPagamento::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(detalheFormaPagamentos);
    }

}
