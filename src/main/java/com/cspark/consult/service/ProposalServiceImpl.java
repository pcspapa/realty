/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.service;

import com.cspark.consult.entity.realty.Proposal;
import com.cspark.consult.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cspark on 2017. 2. 13..
 */
@Service
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    private ProposalRepository proposalRepository;

    @Override
    public List<Proposal> findAll() {
        return proposalRepository.findAll();
    }

    @Override
    public Page<Proposal> findAll(Pageable pageable) {
        return proposalRepository.findAll(pageable);
    }

    @Override
    public Proposal findOne(long id) {
        return proposalRepository.findOne(id);
    }

    @Override
    public Proposal add(Proposal proposal) {
        return proposalRepository.save(proposal);
    }
}
