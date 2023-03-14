package com.api.servicodepagamento.service.annotations;

import com.api.servicodepagamento.service.validator.ExistIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = {ExistIdValidator.class})
public @interface ExistId {
    String message() default "O id não existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> className();


}
