package com.github.thundermarket.thundermarket.exception;

public enum ErrorMessage {
    UNAUTHORIZED("계정 정보가 일치하지 않습니다."),
    FORBIDDEN("로그인이 필요합니다."),
    INTERNAL_SERVER_ERROR("Internal Server Error");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
