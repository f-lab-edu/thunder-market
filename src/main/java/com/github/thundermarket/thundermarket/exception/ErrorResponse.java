package com.github.thundermarket.thundermarket.exception;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class ErrorResponse {
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
