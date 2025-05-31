package com.abv.bookstore.pos.modules.stock.dto;

import com.abv.bookstore.pos.common.domain.StockMovementType;

public record BookStockResponse(
        String message,
        Long booKId,
        StockMovementType stockMovementType,
        Integer quantity,
        String reason
) {
}
