package com.alertinnovation.vending.Exceptions;

public class DispenseItemException extends RuntimeException {
    private String message;

    public DispenseItemException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

