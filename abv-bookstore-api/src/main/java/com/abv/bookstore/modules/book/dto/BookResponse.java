package com.abv.bookstore.modules.book.dto;

public record BookResponse(
        Long bookId,
        String title,
        String description
) {
}
