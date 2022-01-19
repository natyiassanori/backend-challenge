package com.userapi.controller;

import com.userapi.dto.ReplacePhoneNumberDto;
import com.userapi.dto.mapper.PhoneNumberMapper;
import com.userapi.service.PhoneNumberService;
import com.userapi.support.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phoneNumber")
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Autowired
    private PhoneNumberMapper phoneNumberMapper;

    @PostMapping("/addPhoneNumber")
    public ResponseEntity<String> addPhoneNumber(@RequestBody ReplacePhoneNumberDto replacePhoneNumberDto) throws UserNotFoundException {
        phoneNumberService.create(phoneNumberMapper.toModel(replacePhoneNumberDto), replacePhoneNumberDto.getUserId());
        return new ResponseEntity<>("Phone number succesfully created.", HttpStatus.OK);
    }

    @PutMapping("/updatePhoneNumber")
    public ResponseEntity<String> updatePhoneNumber(@RequestBody ReplacePhoneNumberDto replacePhoneNumberDto) {
        String newPhoneNumber = replacePhoneNumberDto.getNewNumber();
        String oldPhoneNumber = replacePhoneNumberDto.getOldNumber();
        int userId = replacePhoneNumberDto.getUserId();

        phoneNumberService.update(newPhoneNumber, oldPhoneNumber, userId);

        return new ResponseEntity<>("Phone number succesfully updated.", HttpStatus.OK);
    }

}
