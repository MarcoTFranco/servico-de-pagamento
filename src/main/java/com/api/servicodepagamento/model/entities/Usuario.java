package com.api.servicodepagamento.model.entities;

import com.api.servicodepagamento.util.FormaDePagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    private String email;

    @Size(min = 1)
    @ElementCollection
    private final Set<FormaDePagamento> formasDePagamento = new HashSet<>();

    @Deprecated
    public Usuario() {
    }

    public Usuario(@Email @NotBlank String email,
                   @Size(min = 1) Set<FormaDePagamento> formasDePagamento) {
        Assert.notNull(formasDePagamento, "Não pode ser nulo");
        Assert.isTrue(formasDePagamento.size() > 0,
                "Precisa de pelo menos uma forma de pagamento");
        this.email = email;
        this.formasDePagamento.addAll(formasDePagamento);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return this.email;
    }

    public Set<FormaDePagamento> getFormasDePagamento() {
        return this.formasDePagamento;
    }

    public Set<FormaDePagamento> listaDePagamentosAceitos(Restaurante restaurante, Collection<RegrasFraude> regrasFraude) {
        return formasDePagamento.stream()
                .filter(restaurante::pagamentosIguais)
                .filter(formaDePagamento -> {
                    return regrasFraude.stream().allMatch(regra -> regra.aceita(formaDePagamento, this));
                })
                .collect(Collectors.toSet());
    }

    public boolean podePagar(Restaurante restaurante,
                             FormaDePagamento formaDePagamento
            , Collection<RegrasFraude> regrasFraude) {
        return listaDePagamentosAceitos(restaurante, regrasFraude).contains(formaDePagamento);
    }
}
