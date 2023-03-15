package com.api.servicodepagamento.service;

import com.api.servicodepagamento.model.entities.Pagamento;
import com.api.servicodepagamento.repositories.PagamentoOfflineRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentoOfflineService {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private PagamentoOfflineRepository repository;

    @Transactional
    public void persist(Pagamento pagamento) {
        manager.persist(pagamento);
    }

    @Transactional
    public void merge(Pagamento pagamento) {
        manager.merge(pagamento);
    }

    public <T> T find(Class<T> classe, Long id) {
        return manager.find(classe, id);
    }

    public Optional<Pagamento> findByCode(String codigoPagamento) {
        return repository.findByUuid(codigoPagamento);
    }
}

