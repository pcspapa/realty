/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by cspark on 2017. 2. 7..
 */
public class BuildingTest {

    private Building someBuilding;

    @Before
    public void setUp() throws Exception {
        someBuilding = new Building(new Address("12345", "서울시 강서구 방화대로34길 62", "서울시", "마곡경남아너스빌"));
    }

    @Test
    public void createBuilding() throws Exception {
        someBuilding.setBasementFloor(5);
        someBuilding.setGroundFloor(23);
    }

    @Test
    public void equalsBuilding() throws Exception {
        Building otherBuliding = new Building(new Address("12345", "서울시 강서구 방화대로34길 62", "서울시", "마곡경남아너스빌"));

        assertTrue(someBuilding.equals(otherBuliding));
    }
}
