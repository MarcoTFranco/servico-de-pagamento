package com.api.servicodepagamento.model.entities;

import com.api.servicodepagamento.util.FormaDePagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

class UsuarioTest {

    Set<FormaDePagamento> formasDePagamentoUsuario = Set.of(FormaDePagamento.dinheiro,
            FormaDePagamento.elo, FormaDePagamento.visa, FormaDePagamento.hypercard, FormaDePagamento.master);
    Set<FormaDePagamento> formasDePagamentoRestaurante = Set.of(FormaDePagamento.dinheiro,
            FormaDePagamento.elo, FormaDePagamento.maquina, FormaDePagamento.cheque);
    Set<FormaDePagamento> formasDePagamentoRestaurante2 = Set.of(FormaDePagamento.maquina, FormaDePagamento.cheque);
    Usuario usuario = new Usuario("usuario@email.com", formasDePagamentoUsuario);
    Restaurante restaurante = new Restaurante("restaurante", formasDePagamentoRestaurante);
    Restaurante restaurante2 = new Restaurante("restaurante2", formasDePagamentoRestaurante2);

    private static Stream<Arguments> geradorTeste1() {
        return Stream.of(
                Arguments.of(Set.of(FormaDePagamento.elo)),
                Arguments.of(Set.of(FormaDePagamento.visa, FormaDePagamento.cheque)));
    }

    private static Stream<Arguments> geradorTeste2() {
        return Stream.of(
                Arguments.of(Set.of()),
                Arguments.of(Set.of()));
    }

    @DisplayName("todo usuario precisa ter pelo menos uma forma de pagamento")
    @ParameterizedTest
    @MethodSource("geradorTeste1")
    void test1(Set<FormaDePagamento> formas) {
        new Usuario("user@gmail.com", formas);
    }

    @DisplayName("todo usuario precisa ter forma de pagamento")
    @ParameterizedTest
    @MethodSource("geradorTeste2")
    void test2(Set<FormaDePagamento> formaDePagamentos) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("user@gmail.com", formaDePagamentos);
        });
    }

    @DisplayName("retornar os pagamentos aceitos")
    @Test
    void test3() {
        RegrasFraude regrasAceita = (forma, usuario) -> {
            return true;
        };
        Collection<RegrasFraude> regras = List.of(regrasAceita);
        Set<FormaDePagamento> formaDePagamentos = usuario.listaDePagamentosAceitos(restaurante, regras);
        Assertions.assertEquals(2, formaDePagamentos.size());
        Assertions.assertTrue(formaDePagamentos.contains(FormaDePagamento.dinheiro));
        Assertions.assertTrue(formaDePagamentos.contains(FormaDePagamento.elo));
    }

    @DisplayName("deveria retornar vazio se alguma regra retornar falso")
    @Test
    void test4() {
        RegrasFraude regrasNaoAceita = (forma, usuario) -> {
            return false;
        };
        Collection<RegrasFraude> regras = List.of(regrasNaoAceita);
        Set<FormaDePagamento> formaDePagamentos = usuario.listaDePagamentosAceitos(restaurante, regras);
        Assertions.assertTrue(formaDePagamentos.isEmpty());
    }

    @DisplayName("retornar lista vazia")
    @Test
    void test5() {
        RegrasFraude regrasAceita = (forma, usuario) -> {
            return true;
        };
        Collection<RegrasFraude> regras = List.of(regrasAceita);
        Set<FormaDePagamento> formaDePagamentos = usuario.listaDePagamentosAceitos(restaurante2, regras);
        Assertions.assertTrue(formaDePagamentos.isEmpty());
    }

    @DisplayName("retornar lista vazia para usuarios que nao tem pagamentos combinados com o restaurante")
    @Test
    void test6() {
        RegrasFraude regrasAceita = (forma, usuario) -> {
            return false;
        };
        Collection<RegrasFraude> regras = List.of(regrasAceita);
        Set<FormaDePagamento> formaDePagamentos = usuario.listaDePagamentosAceitos(restaurante2, regras);
        Assertions.assertTrue(formaDePagamentos.isEmpty());
    }



}