package com.api.servicodepagamento.model.request;

import com.api.servicodepagamento.model.entities.Usuario;
import com.api.servicodepagamento.util.FormaDePagamento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class UsuarioRequest {

    @Email
    @NotBlank
    private String email;
    @Size(min = 1)
    private final Set<FormaDePagamento> formasDePagamento = new HashSet<>();

    @Deprecated
    public UsuarioRequest() {
    }

    public UsuarioRequest(@Email @NotBlank String email,
                          @Size(min = 1) Set<FormaDePagamento> formasDePagamento) {
        this.email = email;
        this.formasDePagamento.addAll(formasDePagamento);
    }

    public String getEmail() {
        return email;
    }

    public Set<FormaDePagamento> getFormasDePagamento() {
        return formasDePagamento;
    }

    public Usuario toModel() {
        return new Usuario(email , formasDePagamento);
    }
}
