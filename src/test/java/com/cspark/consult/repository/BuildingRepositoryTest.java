/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.repository;

import com.cspark.consult.entity.Address;
import com.cspark.consult.entity.Building;
import com.cspark.consult.entity.BuildingContact;
import com.cspark.consult.entity.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by cspark on 2017. 2. 7..
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class BuildingRepositoryTest {

    @Autowired
    private BuildingRepository buildingRepository;

    private Building someBuilding;

    @Before
    public void setUp() {
        someBuilding = new Building(new Address("12345", "서울시 강서구 방화대로34길 62", "서울시", "마곡경남아너스빌"));
    }

    @Test
    public void saveBuilding() throws Exception {
        buildingRepository.save(someBuilding);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveEqualsBuildingThrowException() {
        Building otherBuliding = new Building(new Address("12345", "서울시 강서구 방화대로34길 62", "서울시", "마곡경남아너스빌"));
        buildingRepository.save(someBuilding);
        buildingRepository.save(otherBuliding);
    }

    @Test
    public void addContact() throws Exception {
        Building building = buildingRepository.findOne(1L);
        building.addBuildingContact(new BuildingContact(new Contact(1L), "건물주"));

        assertThat(building.getBuildingContacts().size(), is(1));
    }
}
