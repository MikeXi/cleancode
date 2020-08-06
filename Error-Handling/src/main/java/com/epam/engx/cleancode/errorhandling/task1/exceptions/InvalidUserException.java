package com.epam.engx.cleancode.errorhandling.task1.exceptions;

public class InvalidUserException extends RuntimeException{
    String message = "WARNING: User ID doesn't exist.";

    @Override
    public String getMessage(){
        return message;
    }
}
