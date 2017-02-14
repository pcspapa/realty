/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.repository;

import com.cspark.consult.entity.realty.Address;
import com.cspark.consult.entity.realty.Building;
import com.cspark.consult.entity.realty.BuildingContact;
import com.cspark.consult.entity.realty.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    public void insertBuilding() {
        buildingRepository.save(someBuilding);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void insertBuilding_EqualsBuildingThrowException() {
        Building otherBuliding = new Building(new Address("12345", "서울시 강서구 방화대로34길 62", "서울시", "마곡경남아너스빌"));
        buildingRepository.save(someBuilding);
        buildingRepository.save(otherBuliding);
    }

    @Test
    public void addContact() {
        Building building = buildingRepository.findOne(1L);
        building.addBuildingContact(new BuildingContact(new Contact(3L), "건물주"));

        assertThat(building.getBuildingContacts().size(), is(2));
    }

    @Test
    public void findBuilding() {
        Building building = buildingRepository.findOne(1L);

        assertThat(building.getAddress().getBuildingName(), is("국회도서관"));
    }

    @Test
    public void fineBuildings() {
        List<Building> buildings = buildingRepository.findAll();

        assertThat(buildings.size(), is(2));
        assertThat(buildings.get(0).getBuildingContacts().size(), is(2));
        assertThat(buildings.get(1).getBuildingContacts().size(), is(1));
    }

    @Test
    public void updateBuilding() {
        Building before = buildingRepository.findOne(1L);
        before.setBasementFloor(100);
        buildingRepository.save(before);

        Building after = buildingRepository.findOne(1L);

        assertThat(after.getBasementFloor(), is(100));
    }

    @Test
    public void updateBuildingContact() {
        Building before = buildingRepository.findOne(1L);

        before.getBuildingContacts()
                .stream()
                .filter(bc -> bc.getDirector().equals("건물주"))
                .forEach(bc -> bc.setDirector("관리실"));

        Building after = buildingRepository.findOne(1L);

        assertThat(
                after.getBuildingContacts()
                        .stream()
                        .filter(bc -> bc.getDirector().equals("건물주"))
                        .count(), is(0L)
        );
    }

    @Test
    public void deleteBuildingContact() {
        Building before = buildingRepository.findOne(1L);
        BuildingContact bc = before.getBuildingContacts().iterator().next();
        before.getBuildingContacts().remove(bc);

        Building after = buildingRepository.findOne(1L);

        assertThat(after.getBuildingContacts().size(), is(1));
    }

}
