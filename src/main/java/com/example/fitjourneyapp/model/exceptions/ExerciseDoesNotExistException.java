package com.example.fitjourneyapp.model.exceptions;

public class ExerciseDoesNotExistException extends RuntimeException{
    public ExerciseDoesNotExistException(Long id) {
        super(String.format("Exercise with %d ID does not exist",id));
    }
}
