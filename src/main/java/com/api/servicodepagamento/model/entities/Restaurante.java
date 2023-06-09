package com.api.servicodepagamento.model.entities;

import com.api.servicodepagamento.util.FormaDePagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_restaurante")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @Size(min = 1)
    @ElementCollection
    private final Set<FormaDePagamento> formasDePagamento = new HashSet<>();

    @Deprecated
    public Restaurante() {
    }

    public Restaurante(@NotBlank String nome,
                       @Size(min = 1) Set<FormaDePagamento> formasDePagamento) {
        Assert.notNull(formasDePagamento, "Não pode ser nulo");
        Assert.isTrue(formasDePagamento.size() > 0,
                "Precisa de pelo menos uma forma de pagamento");
        this.nome = nome;
        this.formasDePagamento.addAll(formasDePagamento);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Set<FormaDePagamento> getFormasDePagamento() {
        return formasDePagamento;
    }

    public boolean pagamentosIguais(FormaDePagamento forma) {
        return formasDePagamento.contains(forma);
    }

}
