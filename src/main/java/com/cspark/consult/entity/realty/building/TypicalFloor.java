/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity.realty.building;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.Digits;

/**
 * 기준층 (typical floor)
 *
 * Created by cspark on 2017. 1. 12..
 */
@Embeddable
public class TypicalFloor {

    /**
     * 임대
     */
    @Embedded
    private Area rental;

    /**
     * 전용
     */
    @Embedded
    private Area exclusive;

    /**
     * 전용율 (Exclusive Ratio)
     */
    @Digits(integer = 2, fraction = 0)
    @Column(precision = 2)
    private Integer exclusiveRatio;

    /**
     * 층고 (TargetFloor Height)
     */
    @Digits(integer = 2, fraction = 0)
    @Column(precision = 2)
    private Integer floorHeight;

    public Area getRental() {
        return rental;
    }

    public void setRental(Area rental) {
        this.rental = rental;
    }

    public Area getExclusive() {
        return exclusive;
    }

    public void setExclusive(Area exclusive) {
        this.exclusive = exclusive;
    }

    public Integer getExclusiveRatio() {
        return exclusiveRatio;
    }

    public void setExclusiveRatio(Integer exclusiveRatio) {
        this.exclusiveRatio = exclusiveRatio;
    }

    public Integer getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(Integer floorHeight) {
        this.floorHeight = floorHeight;
    }
}
