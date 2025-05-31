package com.abv.bookstore.pos.modules.stock.service;

import com.abv.bookstore.pos.common.service.BaseService;
import com.abv.bookstore.pos.modules.stock.dto.BookStockResponse;
import com.abv.bookstore.pos.modules.stock.dto.BookStockUpdateRequest;

public interface StockService extends BaseService<BookStockUpdateRequest, BookStockResponse,Long> {
    BookStockResponse updateBookStock(BookStockUpdateRequest bookStockUpdateRequest);
    boolean isStockAvailable(int requestQuantity,Long bookId);
}
