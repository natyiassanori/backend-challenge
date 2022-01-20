package com.userapi.dto.mapper;

import com.userapi.dto.UserDto;
import com.userapi.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private PhoneNumberMapper phoneNumberMapper;

    @Mock
    private EmailMapper emailMapper;

    @Test
    public void shouldReturnUserEntityWhenReceiveUserDto(){
        UserDto userDto = createUserDto();

        Mockito.lenient().when(emailMapper.toModelSet(userDto.getEmails(), new User())).thenReturn(new HashSet<>());
        Mockito.lenient().when(phoneNumberMapper.toModelSet(userDto.getPhoneNumbers(), new User())).thenReturn(new HashSet<>());

        User user = userMapper.toModel(userDto);

        Assert.assertEquals(userDto.getId(), user.getId());
        Assert.assertEquals(userDto.getFirstName(), user.getFirstName());
        Assert.assertEquals(userDto.getLastName(), user.getLastName());
        Assert.assertEquals(userDto.getEmails().size(), user.getEmails().size());
        Assert.assertEquals(userDto.getPhoneNumbers().size(), user.getPhoneNumbers().size());
    }

    @Test
    public void shouldReturnUserDtoWhenReceiveUserEntity(){
        User user = createUser();

        UserDto userDto = userMapper.toDto(user);

        Mockito.lenient().when(emailMapper.toDtoSet(user.getEmails())).thenReturn(new HashSet<>());
        Mockito.lenient().when(phoneNumberMapper.toDtoSet(user.getPhoneNumbers())).thenReturn(new HashSet<>());

        Assert.assertEquals(user.getId(), userDto.getId());
        Assert.assertEquals(user.getFirstName(), userDto.getFirstName());
        Assert.assertEquals(user.getLastName(), userDto.getLastName());
        Assert.assertEquals(user.getEmails().size(), userDto.getEmails().size());
        Assert.assertEquals(user.getPhoneNumbers().size(), userDto.getPhoneNumbers().size());
    }

    private UserDto createUserDto(){
        UserDto userDto = new UserDto();
        userDto.setId(2);
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setEmails(new HashSet<>());
        userDto.setPhoneNumbers(new HashSet<>());
        return userDto;
    }

    private User createUser(){
        User user = new User();
        user.setId(2);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmails(new HashSet<>());
        user.setPhoneNumbers(new HashSet<>());
        return user;
    }
}
