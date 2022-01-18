package com.userapi.controller;

import com.userapi.domain.Email;
import com.userapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/{id}")
    public ResponseEntity getEmailById(@PathVariable int id) {

        try {

            Email email = emailService.findById(id);

            return new ResponseEntity<>(email, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Email());
        }
    }
}
