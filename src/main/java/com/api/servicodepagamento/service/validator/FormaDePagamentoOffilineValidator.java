package com.api.servicodepagamento.service.validator;

import com.api.servicodepagamento.model.request.PedidoOfflineRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FormaDePagamentoOffilineValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PedidoOfflineRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        PedidoOfflineRequest request = (PedidoOfflineRequest) target;
        if (!request.eOffiline()) {
            errors.rejectValue("formaDePagamento", null,
                    "A forma de pagamento tem que ser offline");
        }
    }
}
