package com.practice.cashnoteservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Email
    private String email;

    private String phoneNumber;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "balance_id")
    private Balance balance;



}
