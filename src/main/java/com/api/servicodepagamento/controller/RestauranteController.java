package com.api.servicodepagamento.controller;

import com.api.servicodepagamento.model.entities.Restaurante;
import com.api.servicodepagamento.model.request.RestauranteRequest;
import com.api.servicodepagamento.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class RestauranteController {

    @Autowired
    private RestauranteService service;

    @PostMapping(value = "/restaurantes")
    public ResponseEntity<Restaurante> insert(@RequestBody @Valid RestauranteRequest request) {
        Restaurante restaurante = request.toModel();
        service.insert(restaurante);
        return ResponseEntity.ok().body(restaurante);
    }

}
