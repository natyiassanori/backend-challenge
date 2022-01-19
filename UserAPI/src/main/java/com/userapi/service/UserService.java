package com.userapi.service;

import com.userapi.model.User;
import com.userapi.repository.UserRepository;
import com.userapi.support.EmailDuplicatedException;
import com.userapi.support.InvalidEmailException;
import com.userapi.support.UserNotFoundException;
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

    @Autowired
    private EmailService emailService;

    public User findById(int id) throws UserNotFoundException {
        User user = userRepository.findById(id);

        if(user == null)
            throw new UserNotFoundException(id);

        return user;
    }

    public Set<User> findByLastName(String lastName) throws UserNotFoundException {

        Set<User> users = userRepository.findByLastNameIgnoreCase(lastName);

        if(users.isEmpty())
            throw new UserNotFoundException(lastName);

        return users;
    }

    public Set<User> findByFirstName(String firstName) throws UserNotFoundException {

        Set<User> users = userRepository.findByFirstNameIgnoreCase(firstName);

        if(users.isEmpty())
            throw new UserNotFoundException(firstName);

        return userRepository.findByFirstNameIgnoreCase(firstName);
    }

    public void save(User user) throws EmailDuplicatedException, InvalidEmailException {
        emailService.searchForDuplicateEmails(user.getEmails(), user.getId());
        userRepository.save(user);
    }

    public void delete(int id) throws UserNotFoundException {
        findById(id);
        userRepository.deleteById(id);
    }

}
