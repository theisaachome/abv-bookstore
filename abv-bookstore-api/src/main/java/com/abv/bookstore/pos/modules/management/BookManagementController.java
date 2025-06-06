package com.abv.bookstore.pos.modules.management;

import com.abv.bookstore.pos.common.controller.BaseController;
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
public class BookManagementController extends BaseController<BookRequest, BookResponse,Long> {

    private final BookService bookService;
    public BookManagementController(BookService bookService) {
        super(bookService);
        this.bookService = bookService;
    }

//    GET /books?title=java&authorName=Martin&page=0&size=10

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<BookResponse>>> searchBooks(
            @ModelAttribute BookFilter bookFilter,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int limit,
            @RequestParam(value = "sort", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sort,
            @RequestParam(value = "direction", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String direction

    ) {
        var paginatedResults = bookService.searchAndPaginate(bookFilter,page,limit,sort,direction);
        return ResponseEntity.status(HttpStatus.OK)
                .body(respondPage(paginatedResults,"Books fetched"));
    }

    @PostMapping("/{bookId}/prices")
    public ResponseEntity<ApiResponse<BookPriceResponse>> addPromotionPrice(@PathVariable("bookId")Long bookId,
                                                                            @RequestBody @Valid BookPriceRequest request){
        var result = bookService.addBookPrice(bookId,request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("new book price has been added successfully.",result));
    }
}
