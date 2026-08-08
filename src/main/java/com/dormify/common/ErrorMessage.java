package com.dormify.common;

public enum ErrorMessage {
    USER_WITH_NATIONAL_ID_ALREADY_EXISTS("User with national id %s already exists"),
    USER_WITH_FACULTY_NUMBER_ALREADY_EXISTS("User with faculty number %s already exists"),
    USER_WITH_EMAIL_ALREADY_EXISTS("User with email %s already exists"),
    USER_WITH_PHONE_NUMBER_ALREADY_EXISTS("User with phone number %s already exists");

    private final String messageTemplate;

    ErrorMessage(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getMessage(Object... args) {
        String.format(messageTemplate, args);
    }

}
