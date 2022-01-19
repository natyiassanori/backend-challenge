package com.userapi.support;

public class InvalidEmailException extends Exception{

    private String email;

    public InvalidEmailException(String email){
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "The email '" + email + "' is in the wrong format.";
    }
}
