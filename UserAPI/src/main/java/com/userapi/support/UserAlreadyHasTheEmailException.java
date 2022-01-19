package com.userapi.support;

public class UserAlreadyHasTheEmailException extends Exception{

    private String email;
    private int userId;


    public UserAlreadyHasTheEmailException(String email, int userId) {
        this.email = email;
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return "The user with id '" + userId + "' already has the email '" + email + "'.";
    }
}
