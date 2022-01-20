package com.userapi.service;

import com.userapi.repository.PhoneNumberRepository;
import com.userapi.support.PhoneNumberNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PhoneNumberServiceTest {

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @InjectMocks
    private PhoneNumberService phoneNumberService;

    @Test(expected = PhoneNumberNotFoundException.class)
    public void shouldThrowPhoneNumberNotFoundExceptionWhenTryingToUpdateANonExistentPhoneNumber() throws PhoneNumberNotFoundException {
        int userId = 1;
        String oldNumber = "000";
        String newNumber = "111";

        Mockito.when(phoneNumberRepository.findByNumberAndUserId(oldNumber, userId)).thenReturn(null);

        phoneNumberService.update(newNumber, oldNumber, userId);
    }
}
