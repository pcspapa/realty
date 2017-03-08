/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.service;

import com.cspark.consult.entity.realty.Building;
import com.cspark.consult.entity.realty.BuildingContact;
import com.cspark.consult.entity.realty.Contact;
import com.cspark.consult.entity.realty.building.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by cspark on 2017. 2. 7..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BuildingServiceTest {

    @Autowired
    private BuildingService buildingService;

    private Building someBuilding;

    @Before
    public void setUp() throws Exception {
        someBuilding = new Building(new Address("12345", "서울시 강서구 방화대로34길 62", "서울시", "마곡경남아너스빌"));
    }

    @Test
    public void build() {
        Building building = buildingService.build(someBuilding);

        assertThat(building.getId(), is(notNullValue()));
    }

    @Test
    public void findBuilding() {
        Building building = buildingService.findOne(1L);

        assertThat(building.getBuildingContacts().size(), is(2));
    }

    @Test
    public void findBuilding_addBuildingContact() {
        Building building = buildingService.findOne(1L);
        building.addBuildingContact(new BuildingContact(new Contact(3L), "관리실"));

        assertThat(building.getBuildingContacts().size(), is(3));
    }


    @Test
    public void findBuildings() {
        List<Building> buildings = buildingService.findAll();

        assertThat(buildings.size(), is(2));
        assertThat(buildings.get(0).getBuildingContacts().size(), is(2));
        assertThat(buildings.get(1).getBuildingContacts().size(), is(1));
    }

    @Test
    public void addEqualsContactAndDiffDirector() {
        Building building = buildingService.addContact(1L, 1L, "관리실");

        assertThat(building.getBuildingContacts().size(), is(3));
    }

    /**
     * TODO : DDL add UIX ?
     */
    @Test
    public void addDiffContactAndEqualsDirector() {
        Building building = buildingService.addContact(1L, 3L, "건물주");

        building.addBuildingContact(new BuildingContact(new Contact(3L), "건물주"));

        assertThat(building.getBuildingContacts().size(), is(2));
    }

    @Test
    public void rebuild() {
        Building befoe = buildingService.findOne(1L);
        befoe.setGroundFloor(100);
        buildingService.rebuild(befoe);

        Building after = buildingService.findOne(1L);

        assertThat(after.getGroundFloor(), is(100));
    }


}
