package com.abv.bookstore.pos.modules.admin;
import com.abv.bookstore.pos.common.controller.ResponseHelper;
import com.abv.bookstore.pos.common.domain.ApiResponse;
import com.abv.bookstore.pos.common.domain.PageResponse;
import com.abv.bookstore.pos.common.util.AppConstants;
import com.abv.bookstore.pos.modules.stock.dto.StockMovementDtoView;
import com.abv.bookstore.pos.modules.stock.dto.StockFilters;
import com.abv.bookstore.pos.modules.stock.dto.StockMovementDto;
import com.abv.bookstore.pos.modules.stock.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/books/stocks")
public class BookStockController {
    private final StockService stockService;
    public BookStockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StockMovementDto>> create(@RequestBody StockMovementDto dto) {
      var result =stockService.updateBookStock(dto);
      return ResponseEntity.status(HttpStatus.CREATED)
              .body(new ApiResponse<>("success",result));
    }
    /*
    ✅ 3. View Stock History
    List all movements for a book (with filters by date/type/user).
    Useful Filters:
    bookId
    stockMovementType
    date range
    performedBy
     */
    @GetMapping("/history")
    public ResponseEntity<ApiResponse<PageResponse<StockMovementDtoView>>> viewAndSearchStockHistory(
            @ModelAttribute StockFilters stockFilters,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int limit,
            @RequestParam(value = "sort", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sort,
            @RequestParam(value = "direction", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false)
            String direction){
       var stockPages=  stockService.getStockHistory(stockFilters, page, limit, sort, direction);
        return  ResponseEntity.status(HttpStatus.OK)
                .body(ResponseHelper.respondPage(stockPages,"data fetched"));
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
