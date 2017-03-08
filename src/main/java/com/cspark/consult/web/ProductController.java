/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.web;

import com.cspark.consult.service.BuildingService;
import com.cspark.consult.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cspark on 2017. 2. 28..
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private OfficeService officeService;

    @RequestMapping(value = "/buildings", method = RequestMethod.GET)
    public String buildings(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("buildings", buildingService.findAll(pageable));

        return "product/building-list";
    }

    @RequestMapping(value = "/offices", method = RequestMethod.GET)
    public String offices(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("offices", officeService.findAll(pageable));

        return "product/office-list";
    }
}
