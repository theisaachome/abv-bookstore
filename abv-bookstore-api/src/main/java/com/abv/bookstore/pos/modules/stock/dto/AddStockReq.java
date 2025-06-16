package com.abv.bookstore.pos.modules.stock.dto;

import com.abv.bookstore.pos.common.domain.StockMovementType;
import jakarta.validation.constraints.NotNull;

public record AddStockReq(
        String message,
        Long booKId,
        @NotNull
        StockMovementType stockMovementType,
        @NotNull
        Integer quantity,
        String reason
) {
}
