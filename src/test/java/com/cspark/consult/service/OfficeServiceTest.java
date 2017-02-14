/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.service;

import com.cspark.consult.entity.realty.Building;
import com.cspark.consult.entity.realty.Office;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by cspark on 2017. 2. 8..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OfficeServiceTest {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private OfficeService officeService;

    private Office someOffice;

    @Before
    public void setUp() throws Exception {
        someOffice = new Office(new Office.Item("사무실", "임대"), new Office.Floor(4, null, "전체"));
    }

    @Test
    public void findBuilding_addOffice() {
        Building building = buildingService.findOne(1L);
        building.addOffice(someOffice);

        assertThat(building.getOffices().size(), is(5));
    }

    @Test
    public void build() {
        Office office = officeService.build(1L, someOffice);

        assertThat(office.getId(), is(notNullValue()));
        assertThat(office.getBuilding(), is(notNullValue()));
    }

    @Test
    public void findOne() {
        Office office = officeService.findOne(1L);

        assertThat(office.getBuilding().getAddress().getBuildingName(), is("국회도서관"));
    }

    @Test
    public void findAll() {
        List<Office> offices = officeService.findAll();

        assertThat(offices.size(), is(4));
    }

    @Test
    public void rebuild() {
        someOffice.setId(1L);
        Office office = officeService.rebuild(someOffice);

        assertThat(office.getFloor().getFromValue(), is(4));
    }

    /**
     * TODO : 논리적 삭제를 위한 프로퍼티 추가 (주의 enabled 프로퍼티와 다른 용도임.)
     */
    @Test
    public void sever() {

    }
}
