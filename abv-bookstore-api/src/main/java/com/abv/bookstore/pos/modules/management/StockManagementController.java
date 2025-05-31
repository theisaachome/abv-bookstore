package com.abv.bookstore.pos.modules.management;

import com.abv.bookstore.pos.common.domain.ApiResponse;
import com.abv.bookstore.pos.modules.stock.dto.BookStockResponse;
import com.abv.bookstore.pos.modules.stock.dto.BookStockUpdateRequest;
import com.abv.bookstore.pos.modules.stock.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/books/stocks")
public class StockManagementController {
    private final StockService stockService;
    public StockManagementController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookStockResponse>> create(@RequestBody BookStockUpdateRequest request) {
      var result =stockService.updateBookStock(request);
      return ResponseEntity.status(HttpStatus.CREATED)
              .body(new ApiResponse<>("success",result));
    }
}
/*

---------------------------------------------------------

| Book Title | Current Stock | Actions                    |
| ---------- | ------------- | -------------------------- |
| Book A     | 5             | \[Restock] \[Reduce Stock] |
| Book B     | 12            | \[Restock] \[Reduce Stock] |


        | [📘 Book Title]             | Stock: 12 copies         |
        | Author: John Doe           | Last Movement: 2025-05-30 |
        ---------------------------------------------------------

        [Restock] [Reduce Stock] [View History]

        ---------------------------------------------------------
        [Modal: Restock Book]
        - Stock Movement Type: (INBOUND / OUTBOUND)
        - Quantity: [ 10 ]
        - Reason:   [ Restocked after PO #456 ]
        - Submit / Cancel
---------------------------------------------------------


 */
