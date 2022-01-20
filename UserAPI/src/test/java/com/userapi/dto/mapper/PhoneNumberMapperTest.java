package com.userapi.dto.mapper;

import com.userapi.dto.PhoneNumberDto;
import com.userapi.dto.ReplacePhoneNumberDto;
import com.userapi.model.PhoneNumber;
import com.userapi.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PhoneNumberMapperTest {

    @InjectMocks
    private PhoneNumberMapper phoneNumberMapper;

    @Test
    public void shouldReturnPhoneNumberEntityWhenReceivePhoneNumberDto(){
        PhoneNumberDto phoneNumberDto = createPhoneNumberDto("0000000");
        User user = createUser();

        PhoneNumber phoneNumber = phoneNumberMapper.toModel(phoneNumberDto, user);

        Assert.assertEquals(phoneNumberDto.getNumber(), phoneNumber.getNumber());
        Assert.assertEquals(user.getId(), phoneNumber.getUser().getId());
    }

    @Test
    public void shouldReturnPhoneNumberDtoWhenReceivePhoneNumberEntity(){
        PhoneNumber phoneNumber = createPhoneNumber("000000");

        PhoneNumberDto phoneNumberDto = phoneNumberMapper.toDto(phoneNumber);

        Assert.assertEquals(phoneNumberDto.getNumber(), phoneNumber.getNumber());
    }

    @Test
    public void shouldReturnPhoneNumberWhenReceiveReplacePhoneNumberDto(){
        ReplacePhoneNumberDto replacePhoneNumberDto = createReplacePhoneNumberDto();

        PhoneNumber phoneNumber = phoneNumberMapper.toModel(replacePhoneNumberDto);

        Assert.assertEquals(replacePhoneNumberDto.getNewNumber(), phoneNumber.getNumber());
    }

    @Test
    public void shouldReturnPhoneNumberSetWhenReceivePhoneNumberDtoSet(){
        Set<PhoneNumberDto> phoneNumberDtoSet = new HashSet<>();
        phoneNumberDtoSet.add(createPhoneNumberDto("000000"));

        User user = createUser();

        Set<PhoneNumber> phoneNumberSet = phoneNumberMapper.toModelSet(phoneNumberDtoSet, user);

        String expectedPhoneNumber = phoneNumberDtoSet.iterator().next().getNumber();
        String actualPhoneNumber = phoneNumberSet.iterator().next().getNumber();

        Assert.assertEquals(expectedPhoneNumber, actualPhoneNumber);
        Assert.assertEquals(user.getId(), phoneNumberSet.iterator().next().getUser().getId());
    }

    @Test
    public void shouldReturnPhoneNumberDtoSetWhenReceivePhoneNumberSet(){
        Set<PhoneNumber> phoneNumberSet = new HashSet<>();
        phoneNumberSet.add(createPhoneNumber("000000"));

        Set<PhoneNumberDto> phoneNumberDtoSet = phoneNumberMapper.toDtoSet(phoneNumberSet);

        String actualPhoneNumber = phoneNumberDtoSet.iterator().next().getNumber();
        String expectedPhoneNumber = phoneNumberSet.iterator().next().getNumber();

        Assert.assertEquals(expectedPhoneNumber, actualPhoneNumber);
    }

    private PhoneNumberDto createPhoneNumberDto(String number){
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        phoneNumberDto.setNumber(number);

        return phoneNumberDto;
    }

    private ReplacePhoneNumberDto createReplacePhoneNumberDto(){
        ReplacePhoneNumberDto replacePhoneNumberDto = new ReplacePhoneNumberDto();
        replacePhoneNumberDto.setNewNumber("90099090");
        replacePhoneNumberDto.setOldNumber("00000000");
        replacePhoneNumberDto.setUserId(1);

        return replacePhoneNumberDto;
    }

    private PhoneNumber createPhoneNumber(String number){
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(number);

        return phoneNumber;
    }

    private User createUser(){
        User user = new User();
        user.setId(2);
        return user;
    }
}
