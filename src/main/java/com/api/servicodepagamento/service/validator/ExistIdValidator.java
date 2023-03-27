package com.api.servicodepagamento.service.validator;

import com.api.servicodepagamento.service.annotations.ExistId;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExistIdValidator implements ConstraintValidator<ExistId, Object> {

    @Autowired
    private EntityManager manager;

    private Class<?> className;

    @Override
    public void initialize(ExistId constraintAnnotation) {
        className = constraintAnnotation.className();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        List<?> list = manager.createQuery("select id from " + className.getName() + " where id =:value")
                .setParameter("value", value)
                .getResultList();
        return value == null || list.size() > 0;
    }
}
