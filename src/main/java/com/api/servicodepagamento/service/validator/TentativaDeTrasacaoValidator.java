package com.api.servicodepagamento.service.validator;

import com.api.servicodepagamento.model.request.TentativaDeTransacaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TentativaDeTrasacaoValidator implements Validator {

    @Autowired
    private CombinacaoRestauranteUsuarioFormaPagamentoValidator combinacaoRestauranteUsuarioFormaPagamentoValidator;
    @Autowired
    private FormaPagamentoOffilineValidator formaPagamentoOffilineValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return TentativaDeTransacaoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        combinacaoRestauranteUsuarioFormaPagamentoValidator.validate(target, errors);
        formaPagamentoOffilineValidator.validate(target, errors);
    }
}
