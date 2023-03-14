package com.api.servicodepagamento.service.validator;

import com.api.servicodepagamento.model.entities.RegrasFraude;
import com.api.servicodepagamento.model.entities.Restaurante;
import com.api.servicodepagamento.model.entities.Usuario;
import com.api.servicodepagamento.model.request.TentativaDeTransacaoRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collection;
@Component
public class CombinacaoRestauranteUsuarioFormaPagamentoValidator implements Validator {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private Collection<RegrasFraude> regrasFraudes;

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
        Usuario usuario = manager.find(Usuario.class, request.getIdUsuario());
        Restaurante restaurante = manager.find(Restaurante.class, request.getIdRestaurante());

        boolean podePagar = usuario.podePagar(restaurante, request.getFormaDePagamento(), regrasFraudes);
        if(!podePagar) {
            errors.reject(null, "O usuario e/ou restaurante nao aceita a forma de pagamento");
        }
    }
}
