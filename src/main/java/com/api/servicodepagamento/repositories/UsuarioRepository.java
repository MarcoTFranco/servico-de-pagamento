package com.api.servicodepagamento.repositories;

import com.api.servicodepagamento.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
