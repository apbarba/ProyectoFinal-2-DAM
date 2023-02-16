package com.salesianostriana.dam.imagineria_web.validation.annotation;


import com.salesianostriana.dam.imagineria_web.validation.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
public @interface UniqueEmail {

    String message() default "El gmail ya existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
