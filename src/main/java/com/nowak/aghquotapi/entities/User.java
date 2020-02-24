package com.nowak.aghquotapi.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private  String name;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="token")
    private String token;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="users_authorities", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name="id_authority"))
    private Collection<Authority> usersAuthorities;

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

    public String getPassword() {
        return password;
    }

    public Collection<Authority> getUsersAuthorities() {
        return usersAuthorities;
    }

    public void setUsersAuthorities(Collection<Authority> usersAuthorities) {
        this.usersAuthorities = usersAuthorities;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
