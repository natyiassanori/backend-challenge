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
@Table(name = "phoneNumber", schema = "userdb")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "number")
    private String number;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;
}
