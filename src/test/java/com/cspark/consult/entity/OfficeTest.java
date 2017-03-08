/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity;

import com.cspark.consult.entity.realty.Office;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by cspark on 2017. 2. 8..
 */
public class OfficeTest {

    @Test
    public void createOffice() {
        Office office = new Office(new Office.Item("사무실", "임대"), new Office.TargetFloor(1, null, null));

        assertThat(office.getItem().getTypeCd(), is("사무실"));
        assertThat(office.getItem().getDealCd(), is("임대"));
        assertThat(office.getTargetFloor().getFromValue(), is(1));
    }

}
