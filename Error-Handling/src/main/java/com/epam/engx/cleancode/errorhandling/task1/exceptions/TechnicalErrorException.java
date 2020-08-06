package com.epam.engx.cleancode.errorhandling.task1.exceptions;

public class TechnicalErrorException extends RuntimeException{
    String message = "technicalError";

    @Override
    public String getMessage(){
        return message;
    }
}
