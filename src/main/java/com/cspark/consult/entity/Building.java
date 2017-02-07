/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by cspark on 2017. 2. 7..
 */
@Entity(name = "BUILDING")
@Table(name="person",  uniqueConstraints={
        @UniqueConstraint(columnNames={"zipcode", "street", "city", "buildingName"})
})
public class Building {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    private Integer basementFloor;

    private Integer groundFloor;


    public Building(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public Integer getBasementFloor() {
        return basementFloor;
    }

    public void setBasementFloor(Integer basementFloor) {
        this.basementFloor = basementFloor;
    }

    public Integer getGroundFloor() {
        return groundFloor;
    }

    public void setGroundFloor(Integer groundFloor) {
        this.groundFloor = groundFloor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(address, building.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
