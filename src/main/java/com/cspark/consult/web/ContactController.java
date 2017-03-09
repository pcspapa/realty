/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.web;

import com.cspark.consult.entity.realty.Contact;
import com.cspark.consult.entity.realty.user.RealtyUser;
import com.cspark.consult.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * RESTFul API
 *   - list      : get  : /contacts
 *   - add form  : get  : /contacts/uniting
 *   - add       : post : /contacts
 *   - view      : get  : /contacts/{id}
 *   - edit form : get  : /contacts/{id}/reuniting
 *   - edit      : put  : /contacts
 *
 * Created by cspark on 2016. 10. 21..
 */
@Controller
@RequestMapping("/contacts")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    /**
     * 연락처 목록 화면을 보여준다.
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String contacts(Model model) {
        model.addAttribute("contacts", contactService.findAll());

        return "contacts/contact-list";
    }

    /**
     * 연락처 등록 화면을 보여준다.
     *
     * @param contact
     * @return
     */
    @RequestMapping(value = "/uniting", method = RequestMethod.GET)
    public String contact(Contact contact) {
        return "contacts/writing-contact";
    }

    /**
     * 연락처 등록한다.
     *
     * @param contact
     * @param bindingResult
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String contact(RealtyUser realtyUser, @ModelAttribute @Valid Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("{}", bindingResult.getAllErrors());
            return "contacts/writing-contact";
        }
        contact.setCreatedUser(realtyUser);
        contact.setUpdatedUser(realtyUser);
        contactService.contact(contact);

        return "redirect:/contacts";
    }

    /**
     * 선택된 연락처 상세 화면을 보여준다.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String contact(@PathVariable Long id, Model model) {
        model.addAttribute("contact", contactService.findOne(id));

        return "contacts/reading-contact";
    }

    /**
     * 선택된 연락처를 수정한다.
     *
     * @param realtyUser
     * @param contact
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String recontact(RealtyUser realtyUser, @ModelAttribute @Valid Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("{}", bindingResult.getAllErrors());
            return "contacts/writing-contact";
        }
        contact.setUpdatedUser(realtyUser);
        contactService.recontact(contact);

        return "contacts/reading-contact";
    }

}
