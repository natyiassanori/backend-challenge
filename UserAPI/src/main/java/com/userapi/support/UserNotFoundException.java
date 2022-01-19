package com.userapi.support;

public class UserNotFoundException extends Exception {

    private String message;


    public UserNotFoundException(int id) {
        this.message = "User with id '" + id + "' not found.";
    }

    public UserNotFoundException(String name) {
        this.message = "No user with name '" + name + "' was found.";
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
