package com.alertinnovation.vending.Exceptions;

public class CoinNotSupportedException extends RuntimeException {
    private String message;

    public CoinNotSupportedException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

