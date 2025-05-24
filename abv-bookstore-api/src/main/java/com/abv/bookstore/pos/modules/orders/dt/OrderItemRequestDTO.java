package com.abv.bookstore.pos.modules.orders.dt;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderItemRequestDTO(
        @NotNull
        Long bookId,
        @NotNull
        @Min(value = 1,message = "Quantity must be at least 1")
        Integer quantity,

        // Optional: allow override of unit price (for discounts, special pricing)
        @DecimalMin(value = "0.0", inclusive = true)
        @Digits(integer = 10, fraction = 2)
        BigDecimal unitPrice

) {
}
