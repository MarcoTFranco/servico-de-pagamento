package com.api.servicodepagamento.service;

import com.api.servicodepagamento.model.entities.Pagamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TentativaDeTransacaoService {

    @PersistenceContext
    private EntityManager manager;

    public <T> T find(Class<T> classe, Long id) {
        return manager.find(classe, id);
    }

    @Transactional
    public void insert(Pagamento pagamento) {
        manager.persist(pagamento);
    }

}
