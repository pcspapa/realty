/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity.realty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;

/**
 * Created by cspark on 2017. 2. 13..
 */
@Embeddable
public class Area {

    @Digits(integer=10, fraction=2)
    @Column(precision=12, scale=2, columnDefinition = "DECIMAL(12,2)")
    private Double pyeong;

    @Digits(integer=10, fraction=2)
    @Column(precision=12, scale=2, columnDefinition = "DECIMAL(12,2)")
    private Double squareMeter;

    public Double getPyeong() {
        return pyeong;
    }

    public void setPyeong(Double pyeong) {
        this.pyeong = pyeong;
    }

    public Double getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(Double squareMeter) {
        this.squareMeter = squareMeter;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Area{");
        sb.append("pyeong=").append(pyeong);
        sb.append(", squareMeter=").append(squareMeter);
        sb.append('}');
        return sb.toString();
    }
}
