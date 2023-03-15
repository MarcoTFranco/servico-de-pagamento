package com.api.servicodepagamento.service.validator;

import com.api.servicodepagamento.model.request.PedidoOfflineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TentativaDeTrasacaoValidator implements Validator {

    @Autowired
    private CombinacaoFormaDePagamentoValidator combinacaoFormaDePagamentoValidator;
    @Autowired
    private FormaDePagamentoOffilineValidator formaDePagamentoOffilineValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return PedidoOfflineRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        combinacaoFormaDePagamentoValidator.validate(target, errors);
        formaDePagamentoOffilineValidator.validate(target, errors);
    }
}
