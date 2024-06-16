package com.github.thundermarket.thundermarket.exception;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException() {
    }

    public UnauthenticatedException(String message) {
        super(message);
    }
}
