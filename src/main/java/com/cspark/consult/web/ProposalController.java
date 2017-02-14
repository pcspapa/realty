/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.web;

import com.cspark.consult.entity.realty.Proposal;
import com.cspark.consult.entity.realty.RealtyUser;
import com.cspark.consult.service.ProposalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by cspark on 2017. 2. 13..
 */
@Controller
@RequestMapping("/proposals")
public class ProposalController {

    private static final Logger logger = LoggerFactory.getLogger(ProposalController.class);


    @Autowired
    private ProposalService proposalService;


    @RequestMapping(method = RequestMethod.GET)
    public String proposlas(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("proposals", proposalService.findAll(pageable));

        return "consulting/proposal-list";
    }

    @RequestMapping(value = "/writing", method = RequestMethod.GET)
    public String writing(RealtyUser realtyUser, Proposal proposal, Model model) {
        proposal.setConsultant(realtyUser);
        model.addAttribute("proposal", proposal);

        return "consulting/writing-proposal";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String build(RealtyUser realtyUser, @ModelAttribute @Valid Proposal proposal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("{}", bindingResult.getAllErrors());
            return "consulting/writing-proposal";
        }
        proposal.setCreatedUser(realtyUser);
        proposal.setUpdatedUser(realtyUser);
        proposalService.add(proposal);

        return "redirect:/proposals";
    }

    @RequestMapping(value = "/{id}")
    public String proposal(@PathVariable long id, Model model) {
        model.addAttribute("proposal", proposalService.findOne(id));

        return "consulting/reading-proposal";
    }
}
