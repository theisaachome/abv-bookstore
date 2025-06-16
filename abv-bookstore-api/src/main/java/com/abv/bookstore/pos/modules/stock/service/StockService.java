package com.abv.bookstore.pos.modules.stock.service;

import com.abv.bookstore.pos.common.service.BaseService;
import com.abv.bookstore.pos.modules.stock.dto.AddStockReq;
import com.abv.bookstore.pos.modules.stock.dto.BookStockUpdateRequest;

public interface StockService extends BaseService<BookStockUpdateRequest, AddStockReq,Long> {
    AddStockReq updateBookStock(BookStockUpdateRequest bookStockUpdateRequest);
    boolean isStockAvailable(int requestQuantity,Long bookId);
}
