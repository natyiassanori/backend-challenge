package com.userapi.service;

import com.userapi.domain.PhoneNumber;
import com.userapi.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public PhoneNumber findById(int id) {
        return phoneNumberRepository.findById(id);
    }
}
