/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.repository;

import com.cspark.consult.entity.realty.Consulting;
import com.cspark.consult.entity.realty.Office;
import com.cspark.consult.entity.realty.Proposal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by cspark on 2017. 2. 13..
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ConsultingRepositoryTest {

    @Autowired
    ConsultingRepository consultingRepository;

    @Test
    public void createConsulting() {
        Consulting consulting = new Consulting(new Proposal(4L), new Office(4L));
        consultingRepository.save(consulting);
        consultingRepository.flush();

        assertThat(consulting.getProposal().getItem(), is(nullValue()));
    }


    @Test
    public void selectConsulting() {
        Consulting consulting = consultingRepository.findOne(new Consulting.Id(1L, 1L));

        assertThat(consulting.getProposal().getItem().getType(), is("사무실"));
        assertThat(consulting.getOffice().getItem().getType(), is("사무실"));
    }
}