package com.abv.bookstore.pos.modules.stock.dto;

import com.abv.bookstore.pos.common.domain.StockMovementType;
import com.abv.bookstore.pos.common.util.StockType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookStockUpdateRequest(
        @NotNull
        StockMovementType stockStockMovementType, // IN or OUT
        @Min(1)
        Integer quantity,
        @NotBlank
        String reason
) {
}
