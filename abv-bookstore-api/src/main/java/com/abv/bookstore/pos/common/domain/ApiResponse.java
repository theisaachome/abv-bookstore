package com.abv.bookstore.pos.common.domain;

import java.time.Instant;

public record ApiResponse <T>(
        String status,
        String message,
        T data,
        Instant timestamp
){
    public ApiResponse(String message,T data){
        this("Success", message, null, Instant.now());
    }

}
