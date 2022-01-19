package com.userapi.service;

import com.userapi.domain.User;
import com.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public List<User> findByLastName(String lastName) {
        return userRepository.findByLastNameIgnoreCase(lastName);
    }

    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstNameIgnoreCase(firstName);
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
