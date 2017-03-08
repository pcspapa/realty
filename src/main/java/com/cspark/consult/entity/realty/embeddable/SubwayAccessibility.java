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
public class SubwayAccessibility {

    /**
     * 지하철 라인 코드 (Line Code)
     */
    private String lineCd;

    /**
     * 거리 (Distance)
     */
    @Digits(integer = 4, fraction = 0)
    @Column(precision = 4)
    private Integer distance;

    /**
     * 소요시간 (time, minute)
     */
    @Digits(integer = 3, fraction = 0)
    @Column(precision = 3)
    private Integer minute;

    public String getLineCd() {
        return lineCd;
    }

    public void setLineCd(String lineCd) {
        this.lineCd = lineCd;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SubwayAccessibility{");
        sb.append("lineCd='").append(lineCd).append('\'');
        sb.append(", distance='").append(distance).append('\'');
        sb.append(", minute=").append(minute);
        sb.append('}');
        return sb.toString();
    }
}
