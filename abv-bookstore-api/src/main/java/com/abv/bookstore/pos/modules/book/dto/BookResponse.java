package com.abv.bookstore.pos.modules.book.dto;

public record BookResponse(
        Long bookId,
        String title,
        String description
) {
}
