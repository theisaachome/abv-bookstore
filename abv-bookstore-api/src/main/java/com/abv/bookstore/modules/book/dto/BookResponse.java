package com.abv.bookstore.modules.book.dto;

public record BookResponse(
        Long id,
        String message,
        String status
) {
}
