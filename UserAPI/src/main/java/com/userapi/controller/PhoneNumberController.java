package com.userapi.controller;

import com.userapi.domain.PhoneNumber;
import com.userapi.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/phoneNumber")
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @GetMapping("/{id}")
    public ResponseEntity getPhoneNumberById(@PathVariable int id) {

        try {

            PhoneNumber phoneNumber = phoneNumberService.findById(id);

            return new ResponseEntity<>(phoneNumber, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PhoneNumber());
        }
    }

}
