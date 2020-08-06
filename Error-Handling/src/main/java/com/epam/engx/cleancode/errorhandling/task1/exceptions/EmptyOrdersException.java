package com.epam.engx.cleancode.errorhandling.task1.exceptions;

public class EmptyOrdersException extends RuntimeException{
    String message = "WARNING: User have no submitted orders.";

    @Override
    public String getMessage(){
        return message;
    }
}
