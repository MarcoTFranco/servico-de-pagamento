package com.api.servicodepagamento.service;

import com.api.servicodepagamento.model.entities.Usuario;
import com.api.servicodepagamento.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public void insert(Usuario usuario) {
        repository.save(usuario);
    }

}
