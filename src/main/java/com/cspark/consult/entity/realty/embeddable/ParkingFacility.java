/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity.realty.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;

/**
 * Created by cspark on 2017. 3. 8..
 */
@Embeddable
public class ParkingFacility {

    /**
     * 주차 가능한 수 (Capacity)
     */

    @Digits(integer = 6, fraction = 0)
    @Column(precision = 6)
    private Integer capacity;

    /**
     * 주차 차종 (Type)
     */
    private String carType;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ParkingFacility{");
        sb.append("capacity=").append(capacity);
        sb.append(", carType='").append(carType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
