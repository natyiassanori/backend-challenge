package com.userapi.controller;

import com.userapi.dto.UserDto;
import com.userapi.dto.mapper.UserMapper;
import com.userapi.service.UserService;
import com.userapi.support.EmailDuplicatedException;
import com.userapi.support.InvalidEmailException;
import com.userapi.support.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable int id) throws UserNotFoundException {
        UserDto userDto = userMapper.toDto(userService.findById(id));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(params = "lastName")
    public ResponseEntity getUserByLastName(@RequestParam("lastName") String lastName) throws UserNotFoundException {
        Set<UserDto> userDto = userMapper.toDtoSet(userService.findByLastName(lastName));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(params = "firstName")
    public ResponseEntity getUserByFirstName(@RequestParam("firstName") String firstName) throws UserNotFoundException {
        Set<UserDto> userDto = userMapper.toDtoSet(userService.findByFirstName(firstName));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) throws EmailDuplicatedException, InvalidEmailException {
        userService.save(userMapper.toModel(userDto));
        return new ResponseEntity<>("User succesfully created.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) throws UserNotFoundException {
        userService.delete(id);
        return new ResponseEntity<>("User succesfully deleted.", HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) throws EmailDuplicatedException, InvalidEmailException {
        userService.save(userMapper.toModel(userDto));
        return new ResponseEntity<>("User succesfully updated.", HttpStatus.OK);
    }
}
