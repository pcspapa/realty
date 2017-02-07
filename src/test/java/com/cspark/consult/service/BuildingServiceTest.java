/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.service;

import com.cspark.consult.entity.Address;
import com.cspark.consult.entity.Building;
import com.cspark.consult.entity.BuildingContact;
import com.cspark.consult.entity.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

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
    public void addContact() {
        Building building = buildingService.addContact(1L, 1L, "건물주");

        assertThat(building.getBuildingContacts().size(), is(1));
        assertThat(building.getBuildingContacts().iterator().next().getContact().getId(), is(1L));
    }

    /**
     * TODO : DDL add UIX ?
     */
    @Test
    public void addEqualsContact() {
        Building building = buildingService.addContact(1L, 1L, "건물주");

        building.addBuildingContact(new BuildingContact(new Contact(1L), "건물주"));

        assertThat(building.getBuildingContacts().size(), is(1));
        assertThat(building.getBuildingContacts().iterator().next().getContact().getId(), is(1L));
    }
}
