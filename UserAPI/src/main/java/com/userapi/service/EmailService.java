package com.userapi.service;

import com.userapi.model.Email;
import com.userapi.model.User;
import com.userapi.repository.EmailRepository;
import com.userapi.repository.UserRepository;
import com.userapi.support.EmailDuplicatedException;
import com.userapi.support.InvalidEmailException;
import com.userapi.support.UserAlreadyHasTheEmailException;
import com.userapi.support.EmailNotFoundException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private UserRepository userRepository;

    public void create(Email email, int userId) throws InvalidEmailException, EmailDuplicatedException, UserAlreadyHasTheEmailException {

        searchForWrongFormattedEmail(email.getMail());
        searchForDuplicateEmail(email.getMail(), userId);
        validateIfUserAlreadyHaveTheEmail(email.getMail(), userId);

        User user = userRepository.findById(userId);
        email.setUser(user);

        emailRepository.save(email);
    }

    public void update(String newMail, String oldMail, int userId) throws InvalidEmailException, EmailDuplicatedException, UserAlreadyHasTheEmailException, EmailNotFoundException {

        searchForWrongFormattedEmail(newMail);
        searchForDuplicateEmail(newMail, userId);
        validateIfUserAlreadyHaveTheEmail(newMail, userId);

        Email email = emailRepository.findByMailAndUserId(oldMail, userId);
        if(email == null)
            throw new EmailNotFoundException(oldMail, userId);
        email.setMail(newMail);

        emailRepository.save(email);
    }

    private Email findByMail(String mail){
        return emailRepository.findByMail(mail);
    }

    public void searchForDuplicateEmails(Set<Email> emails, int userId) throws EmailDuplicatedException, InvalidEmailException {
        for(Email email : emails){
            searchForWrongFormattedEmail(email.getMail());
            searchForDuplicateEmail(email.getMail(), userId);
        }
    }

    private void searchForDuplicateEmail(String mail, int userId) throws EmailDuplicatedException {
        Email email = findByMail(mail);
        if(email != null && email.getUser().getId() != userId)
            throw new EmailDuplicatedException(mail);
    }

    private void searchForWrongFormattedEmail(String email) throws InvalidEmailException {
        if(!EmailValidator.getInstance().isValid(email))
            throw new InvalidEmailException(email);
    }

    private void validateIfUserAlreadyHaveTheEmail(String mail, int userId) throws UserAlreadyHasTheEmailException {
        if(emailRepository.findByMailAndUserId(mail, userId) != null)
            throw new UserAlreadyHasTheEmailException(mail, userId);
    }
}
