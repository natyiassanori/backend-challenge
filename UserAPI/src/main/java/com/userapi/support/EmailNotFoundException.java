package com.userapi.support;

public class EmailNotFoundException extends Exception {

    private String email;
    private int userId;

    public EmailNotFoundException(String email, int userId) {
        this.email = email;
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return "The user with id '" + userId + "' does not have the email '" + email + "'.";
    }

}
