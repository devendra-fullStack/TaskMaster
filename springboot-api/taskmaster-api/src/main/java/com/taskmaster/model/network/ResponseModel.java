package com.taskmaster.model.network;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseModel<T> {

    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;
    private int statusCode;

    public static <T> ResponseModel<T> success(String message, T data, int statusCode) {
        return ResponseModel.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .statusCode(statusCode)
                .build();
    }

    public static <T> ResponseModel<T> failure(String message, int statusCode) {
        return ResponseModel.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .timestamp(LocalDateTime.now())
                .statusCode(statusCode)
                .build();
    }
}