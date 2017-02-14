/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.web;

import com.cspark.consult.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cspark on 2017. 2. 13..
 */
@Controller
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;


    @RequestMapping
    public String proposlas(Model model) {
        model.addAttribute("proposals", proposalService.findAll());

        return "proposal-list";
    }

    @RequestMapping(value = "/{id}")
    public String proposal(@PathVariable long id, Model model) {
        model.addAttribute("proposal", proposalService.findOne(id));

        return "reading-proposal";
    }
}
