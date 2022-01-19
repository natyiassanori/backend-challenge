package com.userapi.controller;

import com.userapi.dto.UserDto;
import com.userapi.dto.mapper.UserMapper;
import com.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getById/{id}")
    public ResponseEntity getUserById(@PathVariable int id) {

        try {
            UserDto userDto = userMapper.toDto(userService.findById(id));
            return new ResponseEntity<>(userDto, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @GetMapping("/getByLastName/{lastName}")
    public ResponseEntity getUserByLastName(@PathVariable String lastName) {

        try {
            Set<UserDto> userDto = userMapper.toDtoSet(userService.findByLastName(lastName));
            return new ResponseEntity<>(userDto, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @GetMapping("/getByFirstName/{firstName}")
    public ResponseEntity getUserByFirstName(@PathVariable String firstName) {

        try {
            Set<UserDto> userDto = userMapper.toDtoSet(userService.findByFirstName(firstName));
            return new ResponseEntity<>(userDto, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        userService.save(userMapper.toModel(userDto));
        return new ResponseEntity<>("User succesfully created.", HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>("User succesfully deleted.", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) {
        userService.save(userMapper.toModel(userDto));
        return new ResponseEntity<>("User succesfully updated.", HttpStatus.OK);
    }
}
