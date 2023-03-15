package com.api.servicodepagamento.repositories;

import com.api.servicodepagamento.model.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoOfflineRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByUuid(String codigoPagamento);
}

