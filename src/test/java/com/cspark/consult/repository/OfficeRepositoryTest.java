/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.repository;

import com.cspark.consult.entity.Building;
import com.cspark.consult.entity.Office;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by cspark on 2017. 2. 8..
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class OfficeRepositoryTest {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private OfficeRepository officeRepository;

    private Office someOffice;

    @Before
    public void setUp() throws Exception {
        someOffice = new Office(new Office.Item("사무실", "임대"), new Office.TargetFloor(2, null, "전체"));

    }

    @Test
    public void addOffice() {
        Building building = buildingRepository.findOne(1L);
        building.addOffice(someOffice);
        buildingRepository.flush();

        assertThat(someOffice.getId(), is(notNullValue()));
        assertThat(someOffice.getBuilding(), is(notNullValue()));
        assertThat(someOffice.getBuilding().getAddress(), is(notNullValue()));
        assertThat(someOffice.getBuilding().getAddress().getBuildingName(), is("국회도서관"));
    }

    /**
     * 저장한 gn Office 재 조회 시 Building에 대한 조회가 안 이루어짐. (flush 처리와 상관 없음.)
     */
    @Test
    public void insertOffice() {
        someOffice.setBuilding(new Building(1L));
        officeRepository.save(someOffice);
        officeRepository.flush();

        assertThat(someOffice.getId(), is(notNullValue()));
        assertThat(someOffice.getBuilding(), is(notNullValue()));
        assertThat(someOffice.getBuilding().getAddress(), is(nullValue()));
    }

    @Test
    public void findOffice() {
        Office office = officeRepository.findOne(1L);
        assertThat(office.getId(), is(notNullValue()));
        assertThat(office.getBuilding(), is(notNullValue()));
        assertThat(office.getBuilding().getAddress(), is(notNullValue()));
        assertThat(office.getBuilding().getAddress().getBuildingName(), is("국회도서관"));
   }

   @Test
    public void updateOffice() {
       Office before = officeRepository.findOne(1L);
       before.getItem().setDeal("먜먜");
       officeRepository.flush();

       Office after = officeRepository.findOne(1L);

       assertThat(after.getItem().getDeal(), is("먜먜"));
   }

   @Test
   public void findOffice_updateBuilding() {
       Office before = officeRepository.findOne(1L);
       before.getBuilding().setGroundFloor(100);
       officeRepository.flush();

       Office after = officeRepository.findOne(1L);

       assertThat(after.getBuilding().getGroundFloor(), is(100));
   }

    @Test
    public void deleteOffice() {
        officeRepository.delete(1L);
        officeRepository.flush();

        Office office = officeRepository.findOne(1L);

        assertThat(office, is(nullValue()));
    }

}