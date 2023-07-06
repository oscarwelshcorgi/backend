package com.gg.backend.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false, unique = true) //varchar 50, null 허용 X
    private String email;

    @Column(length = 100, nullable = false)
    private String password;
}
