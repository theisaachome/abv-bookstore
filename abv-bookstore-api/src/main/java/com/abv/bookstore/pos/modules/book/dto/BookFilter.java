package com.abv.bookstore.pos.modules.book.dto;

public record BookFilter(
        String title,
        String sku,
        String author
) {
}
