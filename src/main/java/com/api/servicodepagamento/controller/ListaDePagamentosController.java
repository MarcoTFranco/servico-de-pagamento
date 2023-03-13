package com.api.servicodepagamento.controller;

import com.api.servicodepagamento.model.entities.Restaurante;
import com.api.servicodepagamento.model.entities.Usuario;
import com.api.servicodepagamento.model.request.ListaFormasDePagamentoRequest;
import com.api.servicodepagamento.model.util.FormaDePagamento;
import com.api.servicodepagamento.service.RestauranteService;
import com.api.servicodepagamento.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class ListaDePagamentosController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping(value = "/listas-pagamentos")
    public ResponseEntity<Map<FormaDePagamento, String>> pagamento(@Valid ListaFormasDePagamentoRequest request) {
        Usuario usuario = usuarioService.findById(request.getIdUsuario());
        Restaurante restaurante = restauranteService.findById(request.getIdRestaurante());

        Map<FormaDePagamento, String> map = usuario.listaDePagamentosAceitos(restaurante);

        return ResponseEntity.ok().body(map);
    }

}
