package com.abv.bookstore.pos.modules.stock.dto;

import java.math.BigDecimal;

public record BookStockView(
        Long id,
        String title,
        String sku,
        BigDecimal price,
        Integer currentStock) {
}
