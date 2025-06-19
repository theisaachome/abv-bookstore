package com.abv.bookstore.pos.modules.stock.dto;
import com.abv.bookstore.pos.common.domain.StockMovementType;
import java.time.LocalDate;

public record StockFilters(
        Long bookId,
        String performedBy,
        LocalDate fromDate,
        LocalDate toDate,
        StockMovementType stockMovementType // InBound OR OutBound
) {
}
