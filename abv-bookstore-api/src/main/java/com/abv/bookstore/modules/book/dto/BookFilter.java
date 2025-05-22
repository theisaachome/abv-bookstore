package com.abv.bookstore.modules.book.dto;

public record BookFilter(
        String title,
        String sku,
        String author
) {
}
