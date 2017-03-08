/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity.realty.user;

import javax.persistence.*;

/**
 * Created by cspark on 2017. 2. 14..
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UIX_REALTY_AUTHORITY_USERNAME_ROLE", columnNames = { "username", "role" }))
public class RealtyAuthority {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", foreignKey = @ForeignKey(name = "FK_REALTY_USER_USERNAME"),  nullable = false)
    private RealtyUser realtyUser;

    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RealtyUser getRealtyUser() {
        return realtyUser;
    }

    public void setRealtyUser(RealtyUser realtyUser) {
        this.realtyUser = realtyUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
