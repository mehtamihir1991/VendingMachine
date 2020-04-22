package com.alertinnovation.vending.Exceptions;

public class TransactionCannotBeCancelledException extends RuntimeException {
    private String message;

    public TransactionCannotBeCancelledException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

