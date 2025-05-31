package com.abv.bookstore.pos.modules.stock.dto;

import com.abv.bookstore.pos.common.domain.StockMovementType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookStockUpdateRequest(
        @NotNull(message = "Book Id can't not be null")
        Long bookId,
        @NotNull
        StockMovementType stockStockMovementType, // IN or OUT
        @Min(1)
        Integer quantity,
        @NotBlank
        String reason
) {
}
