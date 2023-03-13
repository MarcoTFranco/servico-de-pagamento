package com.api.servicodepagamento.model.entities;

import com.api.servicodepagamento.util.FormaDePagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

class RestauranteTest {

    private static Stream<Arguments> geradorTeste1() {
        return Stream.of(
                Arguments.of(Set.of(FormaDePagamento.dinheiro)),
                Arguments.of(Set.of(FormaDePagamento.maquina, FormaDePagamento.cheque)));
    }
    private static Stream<Arguments> geradorTeste2() {
        return Stream.of(
                Arguments.of(Set.of()),
                Arguments.of(Set.of()));
    }

    @DisplayName("todo usuario precisa ter pelo menos uma forma de pagamento")
    @ParameterizedTest
    @MethodSource("geradorTeste1")
    void test1(Set<FormaDePagamento> formaDePagamentos) {
        new Restaurante("restaurante", formaDePagamentos);
    }

    @DisplayName("todo usuario precisa ter forma de pagamento")
    @ParameterizedTest
    @MethodSource("geradorTeste2")
    void test2(Set<FormaDePagamento> formaDePagamentos) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Restaurante("restaurante", formaDePagamentos);
        });
    }

}