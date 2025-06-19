package com.abv.bookstore.pos.modules.stock.mapper;
import com.abv.bookstore.pos.common.mapper.BaseMapper;
import com.abv.bookstore.pos.common.util.AppCodeGenerator;
import com.abv.bookstore.pos.common.util.StockTypeReason;
import com.abv.bookstore.pos.modules.stock.dto.StockMovementDtoView;
import com.abv.bookstore.pos.modules.stock.dto.StockMovementDto;
import com.abv.bookstore.pos.modules.stock.entity.StockMovement;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;


@Component
public class StockMovementMapper implements BaseMapper<StockMovementDto, StockMovementDto, StockMovement> {
    @Override
    public StockMovement mapToEntity(StockMovementDto request) {
        StockMovement stockMovement = new StockMovement();
        stockMovement.setReference(AppCodeGenerator.generateStockMovementRef());
        stockMovement.setPerformedBy(request.adjustedBy());
        stockMovement.setStockMovementType(request.stockStockMovementType());
        stockMovement.setReason(request.reason()!=null? request.reason() : StockTypeReason.RESTOCK);
        stockMovement.setQuantity(stockMovement.getStockMovementType().apply(request.quantity()));
        stockMovement.setStockMovementType(request.stockStockMovementType());
        return stockMovement;
    }

    @Override
    public StockMovementDto mapToResponseDTO(StockMovement entity) {
        return new StockMovementDto(entity.getId(),
                entity.getStockMovementType(),
                entity.getQuantity(),
                entity.getReason(),
                entity.getReference(),
                entity.getPerformedBy(),
                entity.getCreatedAt().toInstant(ZoneOffset.UTC),
                entity.getNote()
                );
    }

    @Override
    public StockMovement updateEntity(StockMovementDto request, StockMovement stockMovement) {
        return  stockMovement;
    }
    public StockMovementDtoView mapToStockView(StockMovement entity) {
        var dto = new StockMovementDtoView(
                entity.getId(),
                entity.getBook().getTitle(),
                entity.getBook().getAuthor().getName(),
                entity.getBook().getSku(),
                entity.getStockMovementType(),
                entity.getQuantity(),
                entity.getReason(),
                entity.getPerformedBy(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedAt() );
       return  dto;
    }
}
