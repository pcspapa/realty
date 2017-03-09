/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.constraints.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by cspark on 2016. 12. 15..
 */
public class ByteLengthValidator implements ConstraintValidator<ByteLength, String> {

    private int min;
    private int max;

    @Override
    public void initialize(ByteLength parameters) {
        min = parameters.min();
        max = parameters.max();
        validateParameters();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ( value == null ) {
            return true;
        }

        int byteLength = value.getBytes().length;
        return byteLength >= min && byteLength <= max;
    }

    private void validateParameters() {
        if ( min < 0 ) {
            throw new IllegalArgumentException("The min parameter cannot be negative.");
        }
        if ( max < 0 ) {
            throw new IllegalArgumentException("The max parameter cannot be negative.");
        }
        if ( max < min ) {
            throw new IllegalArgumentException("The length cannot be negative.");
        }
    }
}
