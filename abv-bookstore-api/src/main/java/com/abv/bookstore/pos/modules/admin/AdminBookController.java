package com.abv.bookstore.pos.modules.admin;
import com.abv.bookstore.pos.common.controller.BaseController;
import com.abv.bookstore.pos.common.controller.ResponseHelper;
import com.abv.bookstore.pos.common.domain.ApiResponse;
import com.abv.bookstore.pos.common.domain.PageResponse;
import com.abv.bookstore.pos.common.util.AppConstants;
import com.abv.bookstore.pos.modules.book.dto.*;
import com.abv.bookstore.pos.modules.book.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/books")
public class AdminBookController extends BaseController<BookReq, BookRes,Long>{

    private final BookService bookService;
    public AdminBookController(BookService bookService) {
        super(bookService);
        this.bookService = bookService;
    }

//    GET /books?title=java&authorName=Martin&page=0&size=10
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<BookInventoryDto>>> searchBooks(
            @ModelAttribute BookFilter bookFilter,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int limit,
            @RequestParam(value = "sort", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sort,
            @RequestParam(value = "direction", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false)
            String direction
    ) {
        var paginatedResults = bookService.searchAndPaginate(bookFilter,page,limit,sort,direction);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseHelper.respondPage(paginatedResults,"Books fetched"));
    }



}
/*
POST /admin/books                → create a book
POST /admin/books/{id}/prices   → add a price (promo/in-stock)
PUT  /admin/books/{id}/prices/{priceId} → update a price
POST /admin/books/{id}/stock    → update stock
*/
