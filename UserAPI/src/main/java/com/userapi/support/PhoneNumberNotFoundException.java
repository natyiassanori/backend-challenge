package com.userapi.support;

public class PhoneNumberNotFoundException extends Exception {

    private String phoneNumber;
    private int userId;

    public PhoneNumberNotFoundException(String phoneNumber, int userId) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return "The user with id '" + userId + "' does not have the phone number '" + phoneNumber + "'.";
    }

}
