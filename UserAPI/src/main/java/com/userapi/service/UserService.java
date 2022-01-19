package com.userapi.service;

import com.userapi.model.User;
import com.userapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public Set<User> findByLastName(String lastName) {
        return userRepository.findByLastNameIgnoreCase(lastName);
    }

    public Set<User> findByFirstName(String firstName) {
        return userRepository.findByFirstNameIgnoreCase(firstName);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
