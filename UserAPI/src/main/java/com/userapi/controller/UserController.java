package com.userapi.controller;

import com.userapi.domain.User;
import com.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getById/{id}")
    public ResponseEntity getUserById(@PathVariable int id) {

        try {

            User user = userService.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @GetMapping("/getByLastName/{lastName}")
    public ResponseEntity getUserByLastName(@PathVariable String lastName) {

        try {
            List<User> users = userService.findByLastName(lastName);
            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @GetMapping("/getByFirstName/{firstName}")
    public ResponseEntity getUserByFirstName(@PathVariable String firstName) {

        try {
            List<User> users = userService.findByFirstName(firstName);
            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>("User succesfully created.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> createUser(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>("User succesfully deleted.", HttpStatus.OK);
    }
}
