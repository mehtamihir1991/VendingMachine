package com.alertinnovation.vending.Exceptions;

public class NotsufficientBalanceException extends RuntimeException {
    private String message;

    public NotsufficientBalanceException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

