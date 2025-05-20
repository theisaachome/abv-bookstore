package com.abv.bookstore.modules.book.dto;
import java.math.BigDecimal;

public record BookRequest(
        String title,
        String author,
        String isbn,
        BigDecimal price,
        Integer initialStockQuantity
) {
}
