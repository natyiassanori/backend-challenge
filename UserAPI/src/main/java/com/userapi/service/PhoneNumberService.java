package com.userapi.service;

import com.userapi.model.PhoneNumber;
import com.userapi.model.User;
import com.userapi.repository.PhoneNumberRepository;
import com.userapi.support.PhoneNumberNotFoundException;
import com.userapi.support.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PhoneNumberService {

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public void create(PhoneNumber phoneNumber, int userId) throws UserNotFoundException {

        User user = userService.findById(userId);
        phoneNumber.setUser(user);

        phoneNumberRepository.save(phoneNumber);
    }

    public void update(String newNumber, String oldNumber, int userId) throws PhoneNumberNotFoundException {

        PhoneNumber phoneNumber = phoneNumberRepository.findByNumberAndUserId(oldNumber, userId);

        if(phoneNumber == null)
            throw new PhoneNumberNotFoundException(oldNumber, userId);

        phoneNumber.setNumber(newNumber);

        phoneNumberRepository.save(phoneNumber);
    }
}
