package com.salesianostriana.dam.imagineria_web.validation.validator;

import com.salesianostriana.dam.imagineria_web.validation.annotation.PositivePrice;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositivePriceValidator implements ConstraintValidator<PositivePrice, Double> {

    @Override
    public void initialize(PositivePrice constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double price, ConstraintValidatorContext context) {
        if (price == null) {
            return true;
        }
        return price >= 0;
    }}
