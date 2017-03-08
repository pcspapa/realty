/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.repository;

import com.cspark.consult.entity.realty.Building;
import com.cspark.consult.entity.realty.Office;
import com.cspark.consult.entity.realty.consulting.Proposal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by cspark on 2017. 2. 8..
 */
@ActiveProfiles("test")
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
    public void findBuilding_addOffice() {
        Building building = buildingRepository.findOne(1L);
        building.addOffice(someOffice);
        buildingRepository.flush();

        assertThat(someOffice.getId(), is(notNullValue()));
        assertThat(someOffice.getBuilding(), is(notNullValue()));
        assertThat(someOffice.getBuilding().getAddress(), is(notNullValue()));
        assertThat(someOffice.getBuilding().getAddress().getBuildingName(), is("국회도서관"));
    }

    /**
     * 저장한 후 Office 재 조회 시 Building에 대한 조회가 안 이루어짐. (flush 처리와 상관 없음.)
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
       before.getItem().setDealCd("먜먜");
       officeRepository.flush();

       Office after = officeRepository.findOne(1L);

       assertThat(after.getItem().getDealCd(), is("먜먜"));
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
        officeRepository.delete(4L);
        officeRepository.flush();

        Office office = officeRepository.findOne(4L);

        assertThat(office, is(nullValue()));
    }


    /**
     * TODO : insert consulting 구문을 보지 못 함.
     */
    @Test
    public void createOffice_addProposal() {
        someOffice.setBuilding(new Building(1L));
        officeRepository.save(someOffice);
        officeRepository.flush();

        someOffice.addProposal(new Proposal(1L));

        assertThat(someOffice.getId(), is(notNullValue()));
        assertThat(someOffice.getConsultings().size(), is(1));
    }

    /**
     * TODO : insert consulting 구문을 보지 못 함.
     *
     * 그래서 신규 Proposal을 add 할 경우 에러가 발생 안 함 (기대한 결과는 FK 에러 혹은 Proposal 을 insert)
     * Proposal에서 addOffice도 동일함.
     */
    @Test
    public void findOffice_addProposal() {
        Office office = officeRepository.findOne(1L);
        office.addProposal(new Proposal(4L));

        assertThat(office.getConsultings().size(), is(4));
    }


    @Test
    public void findOffice_duplicateProposal() {
        Office office = officeRepository.findOne(1L);
        office.addProposal(new Proposal(1L));

        assertThat(office.getConsultings().size(), is(3));
    }
}
