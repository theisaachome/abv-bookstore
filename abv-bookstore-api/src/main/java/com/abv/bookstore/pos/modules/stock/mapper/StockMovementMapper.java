package com.abv.bookstore.pos.modules.stock.mapper;
import com.abv.bookstore.pos.common.mapper.BaseMapper;
import com.abv.bookstore.pos.common.util.StockTypeReason;
import com.abv.bookstore.pos.modules.stock.dto.AddStockReq;
import com.abv.bookstore.pos.modules.stock.dto.BookStockUpdateRequest;
import com.abv.bookstore.pos.modules.stock.entity.StockMovement;
import org.springframework.stereotype.Component;


@Component
public class StockMovementMapper implements BaseMapper<BookStockUpdateRequest, AddStockReq, StockMovement> {
    @Override
    public StockMovement mapToEntity(BookStockUpdateRequest request) {
        StockMovement stockMovement = new StockMovement();
        stockMovement.setStockMovementType(request.stockStockMovementType());
        stockMovement.setReason(request.reason()!=null? request.reason() : StockTypeReason.RESTOCK);
        stockMovement.setQuantity(stockMovement.getStockMovementType().apply(request.quantity()));
        stockMovement.setStockMovementType(request.stockStockMovementType());
        return stockMovement;
    }

    @Override
    public AddStockReq mapToResponseDTO(StockMovement stockMovement) {
        return new AddStockReq(
                "Book stock updated",
                stockMovement.getBook().getId(),
                stockMovement.getStockMovementType(),
                stockMovement.getQuantity(),
                stockMovement.getReason()
                );
    }

    @Override
    public StockMovement updateEntity(BookStockUpdateRequest request, StockMovement stockMovement) {
        return  stockMovement;
    }
}
