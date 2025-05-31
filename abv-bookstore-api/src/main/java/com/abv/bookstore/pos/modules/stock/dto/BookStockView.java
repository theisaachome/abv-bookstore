package com.abv.bookstore.pos.modules.stock.dto;
import java.time.ZonedDateTime;

public record BookStockView(
        Long id,
        String sku,
        String title,
        String authors,
        Integer currentStock,
        ZonedDateTime lastRestockedDate,
        ZonedDateTime lastOutboundDate // from frontEnd View History modal would show stock movement history.
) {
}
