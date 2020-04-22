package com.alertinnovation.vending.Exceptions;

public class DispenseChangeException extends RuntimeException {
    private String message;

    public DispenseChangeException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

