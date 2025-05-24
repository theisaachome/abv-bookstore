package com.abv.bookstore.pos.modules.orders.dt;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
         Long id,
         Long bookId,
         String bookTitle,
         String bookIsbn,
         String bookSku,
         Integer quantity,
        BigDecimal unitPrice,
         BigDecimal lineTotal
) {
}
