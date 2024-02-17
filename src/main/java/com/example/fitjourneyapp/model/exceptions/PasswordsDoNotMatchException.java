package com.example.fitjourneyapp.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super("Password do not match");
    }
}