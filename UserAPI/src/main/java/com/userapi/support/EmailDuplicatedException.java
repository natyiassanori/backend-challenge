package com.userapi.support;

public class EmailDuplicatedException extends Exception{

    private String email;

    public EmailDuplicatedException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "The email '" + email + "' is already registered.";
    }
}
