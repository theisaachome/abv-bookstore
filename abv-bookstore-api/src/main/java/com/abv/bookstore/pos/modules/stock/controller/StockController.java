package com.abv.bookstore.pos.modules.stock.controller;

import com.abv.bookstore.pos.common.controller.BaseController;
import com.abv.bookstore.pos.common.domain.ApiResponse;
import com.abv.bookstore.pos.modules.stock.dto.BookStockResponse;
import com.abv.bookstore.pos.modules.stock.dto.BookStockUpdateRequest;
import com.abv.bookstore.pos.modules.stock.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/books")
public class StockController {
    private final StockService stockService;
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/{bookId}/stocks")
    public ResponseEntity<ApiResponse<BookStockResponse>> create(@PathVariable("bookId")Long bookId, @RequestBody BookStockUpdateRequest request) {
      var result =stockService.updateBookStock(bookId,request);
      return ResponseEntity.status(HttpStatus.CREATED)
              .body(new ApiResponse<>("",result));
    }
}
