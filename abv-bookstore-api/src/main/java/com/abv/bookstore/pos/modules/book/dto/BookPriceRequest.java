package com.abv.bookstore.pos.modules.book.dto;

import com.abv.bookstore.pos.common.domain.PriceType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record BookPriceRequest(
        @NotNull(message = "Please price type can not be null")
        PriceType priceType,
        @NotNull(message = "book price is required")
        BigDecimal price,
        @NotNull(message = "start date can not be empty")
        ZonedDateTime startDate,
        @NotNull(message = "end-date can not be null.")
        ZonedDateTime endDate
) {
}
