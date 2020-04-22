package com.alertinnovation.vending.Exceptions;

public class SystemNotReadyException extends RuntimeException {
    private String message;

    public SystemNotReadyException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

