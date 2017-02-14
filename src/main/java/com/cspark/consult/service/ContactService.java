/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.service;

import com.cspark.consult.entity.realty.Contact;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cspark on 2017. 2. 8..
 */
@Transactional(readOnly = true)
public interface ContactService {

    @Transactional
    void contact(Contact contact);

    Contact findOne(long id);

    List<Contact> findAll();


    Contact recontact(Contact contact);

}
