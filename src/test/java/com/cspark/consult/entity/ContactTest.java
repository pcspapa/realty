/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity;

import com.cspark.consult.entity.realty.Contact;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by cspark on 2017. 2. 7..
 */
public class ContactTest {

    private Contact someContact;

    @Before
    public void setUp() throws Exception {
        someContact = new Contact("박찬석", "010-8890-3804", "프리랜서");
    }

    @Test
    public void create() {
    }

    @Test
    public void equalsContact() {
        Contact otherContact = new Contact("박찬석", "010-8890-3804", "국회도서관");

        assertTrue(someContact.equals(otherContact));
    }
}
