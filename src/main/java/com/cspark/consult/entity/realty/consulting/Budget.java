/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity.realty.consulting;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;

/**
 * Created by cspark on 2017. 3. 8..
 */
@Embeddable
public class Budget {

    @Digits(integer = 8, fraction = 0)
    @Column(precision = 8)
    private Long fromValue;

    @Digits(integer = 8, fraction = 0)
    @Column(precision = 8)
    private Long toValue;

    public Long getFromValue() {
        return fromValue;
    }

    public void setFromValue(Long fromValue) {
        this.fromValue = fromValue;
    }

    public Long getToValue() {
        return toValue;
    }

    public void setToValue(Long toValue) {
        this.toValue = toValue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Budget{");
        sb.append("fromValue=").append(fromValue);
        sb.append(", toValue=").append(toValue);
        sb.append('}');
        return sb.toString();
    }
}
