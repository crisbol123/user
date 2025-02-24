package com.pragma.user.adapters.driven.feigns.exception;

public class NoNegativeStockException extends RuntimeException {
    public NoNegativeStockException(String message) {
        super(message);
    }

}
