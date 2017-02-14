/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.repository;

import com.cspark.consult.entity.realty.Contact;
import com.cspark.consult.entity.realty.Office;
import com.cspark.consult.entity.realty.Proposal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by cspark on 2017. 2. 9..
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ProposalRepositoryTest {

    @Autowired
    private ProposalRepository proposalRepository;

    @Test
    public void insertProposal() {
        Proposal proposal = proposalRepository.save(new Proposal(new Contact(1L), new Office.Item("사무실", "임대"), new Proposal.Area(50, 100)));
        proposalRepository.flush();

        assertThat(proposal.getItem().getType(), is("사무실"));
        assertThat(proposal.getItem().getDeal(), is("임대"));
    }

    @Test
    public void findProposal() {
        Proposal proposal = proposalRepository.findOne(1L);

        assertThat(proposal.getItem().getType(), is("사무실"));
        assertThat(proposal.getItem().getDeal(), is("임대"));
        assertThat(proposal.getContact().getCompany(), is("google"));
        assertThat(proposal.getConsultings().size(), is(3));
    }

    @Test
    public void findProposals() {
        List<Proposal> proposals = proposalRepository.findAll();

        assertThat(proposals.size(), is(23));
    }

    @Test
    public void findPageableProposals() {
        Page<Proposal> proposals = proposalRepository.findAll(new PageRequest(1, 10));

        assertThat(proposals.getTotalElements(), is(23L));
        assertThat(proposals.getTotalPages(), is(3));
        assertThat(proposals.getContent().size(), is(10));
        assertThat(proposals.getNumber(), is(1));
        assertThat(proposals.getNumberOfElements(), is(10));
        assertThat(proposals.getSize(), is(10));
        assertThat(proposals.getSort(), is(nullValue()));
    }

    @Test
    public void findPageableProposals_lastPage() {
        Page<Proposal> proposals = proposalRepository.findAll(new PageRequest(2, 10));

        assertThat(proposals.getTotalElements(), is(23L));
        assertThat(proposals.getTotalPages(), is(3));
        assertThat(proposals.getContent().size(), is(3));
        assertThat(proposals.getNumber(), is(2));  // page number ( index : form 0)
        assertThat(proposals.getNumberOfElements(), is(3));
        assertThat(proposals.getSize(), is(10));
        assertThat(proposals.getSort(), is(nullValue()));
    }




    @Test
    public void findProposalAndUpdateProposal() {
        Proposal before = proposalRepository.findOne(1L);
        before.getItem().setType("오피스텔");
        proposalRepository.flush();

        Proposal after = proposalRepository.findOne(1L);

        assertThat(after.getItem().getType(), is("오피스텔"));
    }

    @Test
    public void updateProposal() {
        Proposal proposal = new Proposal(1L, new Contact(1L), new Office.Item("오피스텔", "임대"), new Proposal.Area(50, 100));

        proposalRepository.save(proposal);
        proposalRepository.flush();

        assertThat(proposal.getItem().getType(), is("오피스텔"));
    }


    /**
     * TODO : insert consulting 구문을 보지 못 함.
     */
    @Test
    public void createProposal_addOffice() {
        Proposal proposal = proposalRepository.save(new Proposal(new Contact(1L), new Office.Item("사무실", "임대"), new Proposal.Area(50, 100)));
        proposal.addOffice(new Office(1L));
        proposalRepository.flush();

        assertThat(proposal.getItem(), is(notNullValue()));
        assertThat(proposal.getConsultings().size(), is(1));
    }

    /**
     * TODO : insert consulting 구문을 보지 못 함.
     */
    @Test
    public void findProposal_addOffice() {
        Proposal proposal = proposalRepository.findOne(1L);
        proposal.addOffice(new Office(4L));

        proposal = proposalRepository.findOne(1L);

        assertThat(proposal.getConsultings().size(), is(4));
    }

    @Test
    public void findProposal_duplicateOffice() {
        Proposal proposal = proposalRepository.findOne(1L);
        proposal.addOffice(new Office(1L));

        assertThat(proposal.getConsultings().size(), is(3));
    }

}