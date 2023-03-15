package com.api.servicodepagamento.model.entities;

import com.api.servicodepagamento.util.StatusTrasacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_transacao")
public class Pagamento {
    @Id
    @GeneratedValue
    private Long id;
    private String uuid;
    @NotNull
    private Long idPedido;
    @NotNull
    @Positive
    private BigDecimal valor;
    @ManyToOne
    @NotNull
    private Usuario usuario;
    @ManyToOne
    @NotNull
    private Restaurante restaurante;
    @ElementCollection
    private Set<Transacao> trasacoes = new HashSet<>();

    @Deprecated
    public Pagamento() {
    }

    public Pagamento(@NotNull Long idPedido,
                     @NotNull @Positive BigDecimal valor,
                     @NotNull Usuario usuario,
                     @NotNull Restaurante restaurante,
                     StatusTrasacao statusTrasacao) {
        this.idPedido = idPedido;
        this.valor = valor;
        this.usuario = usuario;
        this.restaurante = restaurante;
        this.trasacoes.add(new Transacao(statusTrasacao));
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Set<Transacao> getTrasacoes() {
        return trasacoes;
    }

    public void conclui() {
        Assert.isTrue(!foiConcluido(),
                "Você não pode concluir uma compra que ja foi concluida");
        this.trasacoes.add(new Transacao(StatusTrasacao.concluida));
    }

    public boolean foiConcluido() {
        return this.trasacoes.stream()
                .anyMatch(transacao -> transacao.temStatus(StatusTrasacao.concluida));
    }
}
