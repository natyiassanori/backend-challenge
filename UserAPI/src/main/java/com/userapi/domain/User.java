package com.userapi.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "user", schema = "userdb")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private int id;

    @Column(name="firstname", nullable = false)
    private String firstName;

    @Column(name="lastname", nullable = false)
    private String lastName;

    @OneToMany(mappedBy="user", targetEntity = Email.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Email> emails;

    @OneToMany(mappedBy="user", targetEntity = PhoneNumber.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<PhoneNumber> phoneNumbers;

}
