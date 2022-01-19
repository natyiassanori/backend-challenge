package com.userapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {

    private int id;

    private String firstName;

    private String lastName;

    private Set<EmailDto> emails;

    private Set<PhoneNumberDto> phoneNumbers;
}
