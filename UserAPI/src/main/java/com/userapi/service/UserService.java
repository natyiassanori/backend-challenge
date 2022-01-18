package com.userapi.service;

import com.userapi.domain.User;
import com.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(int id) {
        return userRepository.findById(id);
    }
}
