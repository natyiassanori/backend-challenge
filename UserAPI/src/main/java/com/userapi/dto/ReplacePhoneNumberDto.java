package com.userapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplacePhoneNumberDto {

    private String oldNumber;

    private String newNumber;

    private int userId;
}
