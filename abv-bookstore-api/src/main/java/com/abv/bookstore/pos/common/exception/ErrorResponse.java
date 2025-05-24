package com.abv.bookstore.pos.common.exception;

import lombok.Data;

import java.time.Instant;

@Data
public class ErrorResponse{
    private String status = "error";
    private String message;
    private String errorCode;
    private String path;
    private Instant timestamp = Instant.now();

}
