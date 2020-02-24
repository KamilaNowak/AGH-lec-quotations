package com.nowak.aghquotapi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="quotations")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="faculty")
    private  String faculty;

    @Column(name="quotation")
    private  String quotation;

}
