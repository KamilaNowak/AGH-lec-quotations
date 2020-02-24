package com.nowak.aghquotapi.service;

import com.nowak.aghquotapi.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetails implements
        org.springframework.security.core.userdetails.UserDetails {


    int id;
    private String name;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetails(int id, String name, String password, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }


    public static UserDetails buildUser(User user) {
        List<GrantedAuthority> grantedAuthorityList =
                user.getUsersAuthorities()
                        .stream().map(authority -> new SimpleGrantedAuthority(
                        authority.getAuthority())).collect(Collectors.toList());
        return new UserDetails(user.getId(), user.getName(), user.getPassword(), user.getEmail(), grantedAuthorityList);
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
    public boolean isEnabled() {
        return true;
    }


}
