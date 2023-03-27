package com.api.servicodepagamento.service;

import com.api.servicodepagamento.model.entities.Restaurante;
import com.api.servicodepagamento.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    public void insert(Restaurante restaurante) {
        repository.save(restaurante);
    }

    public Restaurante findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

}
