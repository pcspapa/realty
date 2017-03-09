/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.repository;

import com.cspark.consult.entity.realty.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by cspark on 2017. 2. 7..
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;
    private Contact someContact;

    @Before
    public void setUp() {
        someContact = new Contact("박찬석", "010-8890-3804", "프리랜서");
    }

    @Test
    public void saveContact() {
        contactRepository.save(someContact);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveEqualsContactThrowException() {
        Contact otherContact = new Contact("박찬석", "010-8890-3804", "국회도서관");
        contactRepository.save(someContact);
        contactRepository.save(otherContact);
    }

    @Test
    public void findContact() {
        Contact contact = contactRepository.findOne(1L);

        assertThat(contact.getCompanyName(), is("google"));
    }

    @Test
    public void findContacts() {
        List<Contact> contacts = contactRepository.findAll();

        assertThat(contacts.size(), is(4));
    }

    @Test
    public void updateContact() {
        Contact before = contactRepository.findOne(1L);
        before.setCompanyName("gooogle");

        Contact after = contactRepository.findOne(1L);

        assertThat(after.getCompanyName(), is("gooogle"));
    }

    @Test
    public void deleteContactWithoutBuildingContact() {
        Contact before = contactRepository.findOne(4L);
        contactRepository.delete(before);
        contactRepository.flush();

        Contact after = contactRepository.findOne(4L);

        assertThat(after, is(nullValue()));
    }

    /**
     * TODO : Rollback(false)으로 설정 시 Failed.
     */
    @Test
    public void deleteContactWithBuildingContact() {
        Contact before = contactRepository.findOne(1L);
        contactRepository.delete(before);

        Contact after = contactRepository.findOne(1L);

        assertThat(after, is(nullValue()));
    }
}
