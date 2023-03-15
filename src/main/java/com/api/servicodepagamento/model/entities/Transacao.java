package com.api.servicodepagamento.model.entities;

import com.api.servicodepagamento.util.StatusTrasacao;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class Transacao {

    private StatusTrasacao statusTrasacao;
    private String codigo;

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusTrasacao statusTrasacao) {
        this.statusTrasacao = statusTrasacao;
        this.codigo = UUID.randomUUID().toString();
    }

    public StatusTrasacao getStatusTrasacao() {
        return statusTrasacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean temStatus(StatusTrasacao concluida) {
        return this.statusTrasacao.equals(concluida);
    }
}
