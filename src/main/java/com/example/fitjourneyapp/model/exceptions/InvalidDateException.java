package com.example.fitjourneyapp.model.exceptions;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException() {
        super("Invalid date was selected");
    }
}
