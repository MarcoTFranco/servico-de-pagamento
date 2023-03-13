package com.api.servicodepagamento.model.entities;

import com.api.servicodepagamento.model.util.FormaDePagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

class UsuarioTest {

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
    Set<FormaDePagamento> formasDePagamentoUsuario = new HashSet<>();
    {
        formasDePagamentoUsuario.add(FormaDePagamento.dinheiro);
        formasDePagamentoUsuario.add(FormaDePagamento.elo);
        formasDePagamentoUsuario.add(FormaDePagamento.visa);
        formasDePagamentoUsuario.add(FormaDePagamento.hypercard);
        formasDePagamentoUsuario.add(FormaDePagamento.master);
    }

    Set<FormaDePagamento> formasDePagamentoRestaurante = new HashSet<>();
    {
        formasDePagamentoRestaurante.add(FormaDePagamento.dinheiro);
        formasDePagamentoRestaurante.add(FormaDePagamento.maquina);
        formasDePagamentoRestaurante.add(FormaDePagamento.cheque);
        formasDePagamentoRestaurante.add(FormaDePagamento.elo);
    }

    Set<FormaDePagamento> formasDePagamentoRestaurante2 = new HashSet<>();
    {
        formasDePagamentoRestaurante2.add(FormaDePagamento.maquina);
        formasDePagamentoRestaurante2.add(FormaDePagamento.cheque);
    }


    Usuario usuario = new Usuario("usuario@email.com", formasDePagamentoUsuario);
    Restaurante restaurante = new Restaurante ("restaurante", formasDePagamentoRestaurante);
    Restaurante restaurante2 = new Restaurante ("restaurante2", formasDePagamentoRestaurante2);

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
        Set<FormaDePagamento> formaDePagamentos = usuario.listaDePagamentosAceitos(restaurante);
        Assertions.assertEquals(2, formaDePagamentos.size());
        Assertions.assertTrue(formaDePagamentos.contains(FormaDePagamento.dinheiro));
        Assertions.assertTrue(formaDePagamentos.contains(FormaDePagamento.elo));
    }

    @DisplayName("retornar lista vazia")
    @Test
    void test4() {
        Set<FormaDePagamento> formaDePagamentos = usuario.listaDePagamentosAceitos(restaurante2);
        Assertions.assertTrue(formaDePagamentos.isEmpty());
    }

}