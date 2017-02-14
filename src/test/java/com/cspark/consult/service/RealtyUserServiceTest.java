/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by cspark on 2017. 2. 14..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RealtyUserServiceTest {

    @Autowired
    private RealtyUserService realtyUserService;

    @Test
    public void loadUserByUsername() {
        UserDetails userDetails = realtyUserService.loadUserByUsername("pcspapa");

        assertThat(userDetails.getAuthorities().size(), is(1));

    }
}