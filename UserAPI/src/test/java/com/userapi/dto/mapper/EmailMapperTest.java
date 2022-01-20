package com.userapi.dto.mapper;

import com.userapi.dto.EmailDto;
import com.userapi.dto.ReplaceEmailDto;
import com.userapi.model.Email;
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
public class EmailMapperTest {

    @InjectMocks
    private EmailMapper emailMapper;

    @Test
    public void shouldReturnEmailEntityWhenReceiveEmailDto(){
        EmailDto emailDto = createEmailDto("example@mail.com");
        User user = createUser();

        Email email = emailMapper.toModel(emailDto, user);

        Assert.assertEquals(emailDto.getMail(), email.getMail());
        Assert.assertEquals(user.getId(), email.getUser().getId());
    }

    @Test
    public void shouldReturnEmailDtoWhenReceiveEmailEntity(){
        Email email = createEmail("example@mail.com");

        EmailDto emailDto = emailMapper.toDto(email);

        Assert.assertEquals(emailDto.getMail(), email.getMail());
    }

    @Test
    public void shouldReturnEmailWhenReceiveReplaceEmailDto(){
        ReplaceEmailDto replaceEmailDto = createReplaceEmailDto();

        Email email = emailMapper.toModel(replaceEmailDto);

        Assert.assertEquals(replaceEmailDto.getNewMail(), email.getMail());
    }

    @Test
    public void shouldReturnEmailSetWhenReceiveEmailDtoSet(){
        Set<EmailDto> emailDtoSet = new HashSet<>();
        emailDtoSet.add(createEmailDto("example@mail.com"));

        User user = createUser();

        Set<Email> emailSet = emailMapper.toModelSet(emailDtoSet, user);

        String expectedMail = emailDtoSet.iterator().next().getMail();
        String actualMail = emailSet.iterator().next().getMail();

        Assert.assertEquals(expectedMail, actualMail);
        Assert.assertEquals(user.getId(), emailSet.iterator().next().getUser().getId());
    }

    @Test
    public void shouldReturnEmailDtoSetWhenReceiveEmailSet(){
        Set<Email> emailSet = new HashSet<>();
        emailSet.add(createEmail("example@mail.com"));

        Set<EmailDto> emailDtoSet = emailMapper.toDtoSet(emailSet);

        String actualMail = emailDtoSet.iterator().next().getMail();
        String expectedMail = emailSet.iterator().next().getMail();

        Assert.assertEquals(expectedMail, actualMail);
    }

    private EmailDto createEmailDto(String mail){
        EmailDto emailDto = new EmailDto();
        emailDto.setMail(mail);

        return emailDto;
    }

    private ReplaceEmailDto createReplaceEmailDto(){
        ReplaceEmailDto replaceEmailDto = new ReplaceEmailDto();
        replaceEmailDto.setNewMail("example@email.com");
        replaceEmailDto.setOldMail("old@email.com");
        replaceEmailDto.setUserId(1);

        return replaceEmailDto;
    }

    private Email createEmail(String mail){
        Email email = new Email();
        email.setMail(mail);

        return email;
    }

    private User createUser(){
        User user = new User();
        user.setId(2);
        return user;
    }
}
