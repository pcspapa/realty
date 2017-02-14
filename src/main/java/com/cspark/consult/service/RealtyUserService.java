/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.service;

import com.cspark.consult.entity.realty.RealtyAuthority;
import com.cspark.consult.entity.realty.RealtyUser;
import com.cspark.consult.repository.RealtyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cspark on 2017. 2. 14..
 */
@Service
@Transactional(readOnly = true)
public class RealtyUserService implements UserDetailsService {

    @Autowired
    private RealtyUserRepository realtyUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return buildUserForAuthentication(realtyUserRepository.findOne(username));
    }

    private UserDetails buildUserForAuthentication(RealtyUser realtyUser) {
        realtyUser.setAuthorities(buildAuthorities(realtyUser.getRealtyAuthorities()));

        return realtyUser;
    }

    private Collection<? extends GrantedAuthority> buildAuthorities(Set<RealtyAuthority> realtyAuthorities) {
        Set<GrantedAuthority> setAuths = new HashSet<>();

        for (RealtyAuthority realtyAuthority : realtyAuthorities) {
            setAuths.add(new SimpleGrantedAuthority(realtyAuthority.getRole()));
        }

        return new ArrayList<>(setAuths);
    }

}
