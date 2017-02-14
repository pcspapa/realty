/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity.realty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cspark on 2017. 2. 14..
 */
@Entity
public class RealtyUser implements UserDetails {

    @Id
    private String username;

    private String password;

    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "realtyUser")
    private Set<RealtyAuthority> realtyAuthorities = new HashSet<>();

    @Transient
    Collection<? extends GrantedAuthority> authorities = new HashSet<>();

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<RealtyAuthority> getRealtyAuthorities() {
        return realtyAuthorities;
    }

    public void setRealtyAuthorities(Set<RealtyAuthority> realtyAuthorities) {
        this.realtyAuthorities = realtyAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RealtyUser{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password=[PROTECTED]");
        sb.append(", enabled=").append(enabled);
        sb.append(", authorities=").append(authorities);
        sb.append('}');
        return sb.toString();
    }
}
