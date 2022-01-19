package com.userapi.service;

import com.userapi.model.Email;
import com.userapi.model.User;
import com.userapi.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private UserService userService;

    public void create(Email email, int userId) {

        User user = userService.findById(userId);
        email.setUser(user);

        emailRepository.save(email);
    }

    public void update(String newMail, String oldMail, int userId) {

        Email email = emailRepository.findByMailAndUserId(oldMail, userId);
        email.setMail(newMail);

        emailRepository.save(email);
    }
}
