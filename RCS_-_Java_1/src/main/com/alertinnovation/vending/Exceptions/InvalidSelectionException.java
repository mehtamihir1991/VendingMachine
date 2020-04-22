package com.alertinnovation.vending.Exceptions;

public class InvalidSelectionException extends RuntimeException {
    private String message;

    public InvalidSelectionException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

