package com.abv.bookstore.pos.modules.stock.service;

import com.abv.bookstore.pos.common.service.BaseService;
import com.abv.bookstore.pos.modules.stock.dto.StockMovementDtoView;
import com.abv.bookstore.pos.modules.stock.dto.StockFilters;
import com.abv.bookstore.pos.modules.stock.dto.StockMovementDto;
import org.springframework.data.domain.Page;

public interface StockService extends BaseService<StockMovementDto, StockMovementDto,Long> {
    StockMovementDto updateBookStock(StockMovementDto  dto);
    boolean isStockAvailable(int requestQuantity,Long bookId);
    Page<StockMovementDtoView> getStockHistory(StockFilters filters, int page, int size, String sortBy, String sortOrder);
}
