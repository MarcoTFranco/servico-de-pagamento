package com.api.servicodepagamento.service.validator;

import com.api.servicodepagamento.model.request.TentativaDeTransacaoRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FormaPagamentoOffilineValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TentativaDeTransacaoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        TentativaDeTransacaoRequest request = (TentativaDeTransacaoRequest) target;
        if (!request.eOffiline()) {
            errors.rejectValue("formaDePagamento", null,
                    "A forma de pagamento tem que ser offline");
        }
    }
}
