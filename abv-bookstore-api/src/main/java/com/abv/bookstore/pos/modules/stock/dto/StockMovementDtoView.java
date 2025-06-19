package com.abv.bookstore.pos.modules.stock.dto;
import com.abv.bookstore.pos.common.domain.StockMovementType;
import lombok.*;

import java.time.LocalDateTime;
@Data
public class StockMovementDtoView {

    private Long id;
    private String sku;
    private String title;
    private String authors;
    private Integer quantity;
    private StockMovementType stockMovementType;
    private String reason;
    private String performedBy;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public StockMovementDtoView(
            Long id,
            String title,
            String authors,
            String sku,
            StockMovementType stockMovementType,
            Integer quantity,
            String reason,
            String performedBy,
            String createdBy,
            String updatedBy,
            LocalDateTime createdDate,
            LocalDateTime updatedDate
    ) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.sku = sku;
        this.stockMovementType = stockMovementType;
        this.quantity = quantity;
        this.reason = reason;
        this.performedBy = performedBy;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

