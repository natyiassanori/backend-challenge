package com.userapi.controller;

import com.userapi.dto.ReplaceEmailDto;
import com.userapi.dto.mapper.EmailMapper;
import com.userapi.service.EmailService;
import com.userapi.support.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailMapper emailMapper;

    @PostMapping("/addEmail")
    public ResponseEntity<String> addEmail(@RequestBody ReplaceEmailDto replaceEmailDto) throws InvalidEmailException,
                                                                                                EmailDuplicatedException,
                                                                                                UserAlreadyHasTheEmailException,
                                                                                                UserNotFoundException {
        emailService.create(emailMapper.toModel(replaceEmailDto), replaceEmailDto.getUserId());
        return new ResponseEntity<>("Email succesfully created.", HttpStatus.OK);
    }

    @PutMapping("/updateEmail")
    public ResponseEntity<String> updateEmail(@RequestBody ReplaceEmailDto replaceEmailDto) throws  InvalidEmailException,
                                                                                                    EmailDuplicatedException,
                                                                                                    UserAlreadyHasTheEmailException,
                                                                                                    EmailNotFoundException {
        String newEmail = replaceEmailDto.getNewMail();
        String oldEmail = replaceEmailDto.getOldMail();
        int userId = replaceEmailDto.getUserId();

        emailService.update(newEmail, oldEmail, userId);

        return new ResponseEntity<>("Email succesfully updated.", HttpStatus.OK);
    }
}
