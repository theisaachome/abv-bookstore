package com.abv.bookstore.pos.modules.book.dto;

import com.abv.bookstore.pos.common.domain.PriceType;
import com.abv.bookstore.pos.modules.book.entity.BookPriceStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public record BookPriceRes(
        Long id,
        BigDecimal price,
        PriceType priceType,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BookPriceStatus status,
        boolean expired,
        boolean current
) {
}
