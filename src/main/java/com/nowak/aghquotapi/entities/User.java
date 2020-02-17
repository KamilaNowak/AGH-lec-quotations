package com.nowak.aghquotapi.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
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
}
