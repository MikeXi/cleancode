package com.epam.engx.cleancode.errorhandling.task1.exceptions;

public class InvalidOrderTotalAmountException extends RuntimeException {
    String message = "ERROR: Wrong order amount.";

    @Override
    public String getMessage(){
        return message;
    }
}
