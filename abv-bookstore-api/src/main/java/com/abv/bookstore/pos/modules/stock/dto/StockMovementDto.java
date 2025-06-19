package com.abv.bookstore.pos.modules.stock.dto;

import com.abv.bookstore.pos.common.domain.StockMovementType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record StockMovementDto(
        @NotNull(message = "Book Id can't not be null")
        Long bookId,

        @NotNull(message = "Stock movement type must be specified")
        StockMovementType stockStockMovementType,

        @NotNull(message = "Quantity is required")
        @Min(value = 1, message = "Quantity must be at least 1")
        Integer quantity,

        @NotBlank(message = "Please provide reason for the movement")
        String reason,

        String reference,        // system will generate this
        String adjustedBy,       // filled by current user (optional for now)
        Instant adjustedAt,      // optional, can default to now if null
        String note              // optional remarks
) {
        // Custom compact constructor for required fields only
        public StockMovementDto(
                Long bookId,
                StockMovementType stockStockMovementType,
                Integer quantity,
                String reason
        ) {
                this(bookId, stockStockMovementType, quantity, reason, null, null, null, null);
        }
}
