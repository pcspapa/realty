/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity;

import com.cspark.consult.entity.realty.Contact;
import com.cspark.consult.entity.realty.Office;
import com.cspark.consult.entity.realty.Proposal;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by cspark on 2017. 2. 8..
 */
public class ProposalTest {

    @Test
    public void createProposal() {
        Proposal proposal = new Proposal(new Contact(1L), new Office.Item("사무실", "임대"), new Proposal.Area(50, 100));

        assertThat(proposal.getItem().getType(), is("사무실"));
    }

}
