package com.userapi.service;

import com.userapi.domain.Email;
import com.userapi.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public Email findById(int id) {
        return emailRepository.findById(id);
    }
}
