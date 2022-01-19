package com.userapi.dto.mapper;

import com.userapi.dto.UserDto;
import com.userapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper {

    @Autowired
    EmailMapper emailMapper;

    @Autowired
    PhoneNumberMapper phoneNumberMapper;

    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmails(emailMapper.toDtoSet(user.getEmails()));
        userDto.setPhoneNumbers(phoneNumberMapper.toDtoSet(user.getPhoneNumbers()));

        return userDto;
    }

    public Set<UserDto> toDtoSet(Set<User> userSet){
        Set<UserDto> userDtoSet = new HashSet<UserDto>();

        for(User user : userSet){
            userDtoSet.add(toDto(user));
        }

        return userDtoSet;
    }

    public User toModel(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmails(emailMapper.toModelSet(userDto.getEmails(), user));
        user.setPhoneNumbers(phoneNumberMapper.toModelSet(userDto.getPhoneNumbers(), user));

        return user;
    }
}
