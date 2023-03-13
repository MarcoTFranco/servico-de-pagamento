package com.api.servicodepagamento.model.entities;

import com.api.servicodepagamento.util.FormaDePagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

class RegraDoPossivelFraudadorTest {

    private static Stream<Arguments> geradorTeste1() {
        Usuario usuarioValido = new Usuario("usuario@gmail.com", Collections.singleton(FormaDePagamento.elo));
        Usuario usuarioInvalido = new Usuario("fraudador@gmail.com", Collections.singleton(FormaDePagamento.elo));

        return Stream.of(
                Arguments.of(FormaDePagamento.dinheiro, usuarioValido, true),
                Arguments.of(FormaDePagamento.elo, usuarioValido, true),
                Arguments.of(FormaDePagamento.hypercard, usuarioInvalido, false)
        );
    }

    @DisplayName("deveria lidar com todo tipo de pagamento")
    @ParameterizedTest
    @MethodSource("geradorTeste1")
    void test1(FormaDePagamento formaDePagamento, Usuario usuario, boolean esperado) {
        RegraDoPossivelFraudador regra = new RegraDoPossivelFraudador();
        boolean aceite = regra.aceita(formaDePagamento, usuario);
        Assertions.assertEquals(aceite, esperado);
    }

}