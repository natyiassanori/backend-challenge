package com.userapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "email", schema = "userdb")
public class Email {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "mail")
    private String mail;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;
}
