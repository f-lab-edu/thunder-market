package com.github.thundermarket.thundermarket.exception;

import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseBody;

@Getter
@ResponseBody
public class ErrorResponse {
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

}
