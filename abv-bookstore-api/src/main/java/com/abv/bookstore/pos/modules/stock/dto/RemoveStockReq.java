package com.abv.bookstore.pos.modules.stock.dto;
import com.abv.bookstore.pos.common.domain.StockMovementType;
import jakarta.validation.constraints.NotNull;

public record RemoveStockReq(
        @NotNull
        Integer quantity,
        Long booKId,
        @NotNull
        StockMovementType stockMovementType,
        String reason
) {
}
