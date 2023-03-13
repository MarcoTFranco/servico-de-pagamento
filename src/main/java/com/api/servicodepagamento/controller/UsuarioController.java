package com.api.servicodepagamento.controller;

import com.api.servicodepagamento.model.entities.Usuario;
import com.api.servicodepagamento.model.request.UsuarioRequest;
import com.api.servicodepagamento.service.RestauranteService;
import com.api.servicodepagamento.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RestauranteService restauranteService;


    @PostMapping(value = "/usuarios")
    public ResponseEntity<Usuario> insert(@RequestBody @Valid UsuarioRequest request) {
        Usuario usuario = request.toModel();
        usuarioService.insert(usuario);
        return ResponseEntity.ok().body(usuario);
    }
}
