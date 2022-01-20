package com.userapi.service;

import com.userapi.model.Email;
import com.userapi.model.User;
import com.userapi.repository.EmailRepository;
import com.userapi.repository.UserRepository;
import com.userapi.support.EmailDuplicatedException;
import com.userapi.support.InvalidEmailException;
import com.userapi.support.EmailNotFoundException;
import com.userapi.support.UserNotFoundException;
import com.userapi.support.UserAlreadyHasTheEmailException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EmailServiceTest {

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private EmailService emailService;

    @Test(expected = InvalidEmailException.class)
    public void shouldThrowInvalidEmailExceptionWhenReceivedEmailForCreationIsInvalid() throws UserAlreadyHasTheEmailException,
            InvalidEmailException,
            EmailDuplicatedException,
            UserNotFoundException {
        int userId = 1;
        Email email = new Email();
        email.setMail("examplemail.com");

        emailService.create(email, userId);
    }

    @Test(expected = EmailDuplicatedException.class)
    public void shouldThrowEmailDuplicatedExceptionWhenReceivedEmailIsAlreadyRegistered() throws UserAlreadyHasTheEmailException,
            InvalidEmailException,
            EmailDuplicatedException,
            UserNotFoundException {
        int userId = 1;
        Email newEmail = new Email();
        String mail = "example@mail.com";
        newEmail.setMail(mail);

        Email existingEmail = new Email();
        User existingUser = new User();
        existingUser.setId(2);
        existingEmail.setMail("example@mail.com");
        existingEmail.setUser(existingUser);

        Mockito.when(emailRepository.findByMail(mail)).thenReturn(existingEmail);

        emailService.create(newEmail, userId);
    }

    @Test(expected = UserAlreadyHasTheEmailException.class)
    public void shouldThrowUserAlreadyHasTheEmailExceptionWhenReceivedEmailAlreadyRegistereForTheReceivedUserId() throws UserAlreadyHasTheEmailException,
            InvalidEmailException,
            EmailDuplicatedException,
            UserNotFoundException {
        int userId = 1;
        Email newEmail = new Email();
        String mail = "example@mail.com";
        newEmail.setMail(mail);

        Email existingEmail = new Email();
        User existingUser = new User();
        existingUser.setId(1);
        existingEmail.setMail("example@mail.com");
        existingEmail.setUser(existingUser);

        Mockito.when(emailRepository.findByMailAndUserId(mail, userId)).thenReturn(existingEmail);

        emailService.create(newEmail, userId);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionWhenGettingANonExistentUserId() throws UserAlreadyHasTheEmailException,
            InvalidEmailException,
            EmailDuplicatedException,
            UserNotFoundException {
        int userId = 3;
        Email newEmail = new Email();
        String mail = "example@mail.com";
        newEmail.setMail(mail);

        Mockito.when(emailRepository.findByMailAndUserId(mail, userId)).thenReturn(null);
        Mockito.when(userRepository.findById(userId)).thenReturn(null);


        emailService.create(newEmail, userId);
    }

    @Test(expected = EmailNotFoundException.class)
    public void shouldThrowEmailNotFoundExceptionWhenUpdatingEmailDoesNotExists() throws UserAlreadyHasTheEmailException,
            InvalidEmailException,
            EmailDuplicatedException,
            EmailNotFoundException {
        int userId = 1;
        String oldEmail = "exampleold@mail.com";
        String newEmail = "example@mail.com";

        Email existingEmail = new Email();
        User existingUser = new User();
        existingUser.setId(1);
        existingEmail.setMail("example@mail.com");
        existingEmail.setUser(existingUser);

        Mockito.when(emailRepository.findByMailAndUserId(oldEmail, userId)).thenReturn(null);

        emailService.update(newEmail, oldEmail, userId);
    }

    @Test(expected = EmailDuplicatedException.class)
    public void shouldThrowEmailDuplicatedExceptionWhenReceivedASetOfEmailsAndOneOfThenIsAlreadyRegistered() throws
            InvalidEmailException,
            EmailDuplicatedException {

        int userId = 1;

        Email email1 = new Email();
        String mail = "example@mail.com";
        email1.setMail(mail);

        Email email2 = new Email();
        String mail2 = "example2@mail.com";
        email2.setMail(mail2);

        Set<Email> emailSet = new HashSet<>();
        emailSet.add(email1);
        emailSet.add(email2);

        Email existingEmail = new Email();
        User existingUser = new User();
        existingUser.setId(2);
        existingEmail.setMail("example2@mail.com");
        existingEmail.setUser(existingUser);

        Mockito.when(emailRepository.findByMail(mail)).thenReturn(existingEmail);

        emailService.searchForDuplicateEmails(emailSet, userId);
    }

    @Test(expected = InvalidEmailException.class)
    public void shouldThrowInvalidEmailExceptionWhenReceivedASetOfEmailsAndOneOfThenIsInvalid() throws
            InvalidEmailException,
            EmailDuplicatedException {

        int userId = 1;

        Email email1 = new Email();
        String mail = "example@mail.com";
        email1.setMail(mail);

        Email email2 = new Email();
        String mail2 = "example2mail.com";
        email2.setMail(mail2);

        Set<Email> emailSet = new HashSet<>();
        emailSet.add(email1);
        emailSet.add(email2);

        emailService.searchForDuplicateEmails(emailSet, userId);
    }
}
