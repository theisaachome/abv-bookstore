package com.abv.bookstore.pos.modules.book.dto;

import com.abv.bookstore.pos.common.domain.PriceType;

import java.time.ZonedDateTime;

public record BookPriceResponse(
        Long id,
        ZonedDateTime startDate,
        ZonedDateTime endDate,
        PriceType priceType,
        String message
) {
}
