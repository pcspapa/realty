/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.service;

import com.cspark.consult.entity.realty.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by cspark on 2017. 2. 8..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Test
    public void contact() {
        contactService.contact(new Contact("박찬석", "010-8890-3804", "프리랜서"));
    }

    @Test
    public void findContact() {
        Contact contact = contactService.findOne(1L);

        assertThat(contact.getCompany(), is("google"));
    }

    @Test
    public void findContacts() {
        List<Contact> contacts = contactService.findAll();

        assertThat(contacts.size(), is(4));
    }

    @Test
    public void recontact() {
        Contact before = contactService.findOne(1L);
        before.setCompany("gooogle");

        Contact after = contactService.recontact(before);

        assertThat(after.getCompany(), is("gooogle"));
    }
}