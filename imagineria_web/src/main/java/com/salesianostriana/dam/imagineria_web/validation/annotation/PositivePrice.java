package com.salesianostriana.dam.imagineria_web.validation.annotation;


import com.salesianostriana.dam.imagineria_web.validation.validator.PositivePriceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = PositivePriceValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PositivePrice {

    String message() default "El precio de la obra debe ser positivo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};}
