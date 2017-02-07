/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.repository;

import com.cspark.consult.entity.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by cspark on 2017. 2. 7..
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;
    private Contact someContact;

    @Before
    public void setUp() throws Exception {
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
}