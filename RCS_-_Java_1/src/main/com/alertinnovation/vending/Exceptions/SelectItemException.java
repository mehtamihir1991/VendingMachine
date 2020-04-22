package com.alertinnovation.vending.Exceptions;

public class SelectItemException extends RuntimeException {
    private String message;

    public SelectItemException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

