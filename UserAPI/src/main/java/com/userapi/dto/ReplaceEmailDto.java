package com.userapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplaceEmailDto {

    private String oldMail;

    private String newMail;

    private int userId;

}
