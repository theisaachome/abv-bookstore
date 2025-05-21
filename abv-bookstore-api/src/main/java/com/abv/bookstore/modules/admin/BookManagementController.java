package com.abv.bookstore.modules.admin;

import com.abv.bookstore.common.controller.BaseController;
import com.abv.bookstore.common.domain.ApiResponse;
import com.abv.bookstore.common.domain.PageResponse;
import com.abv.bookstore.common.util.AppConstants;
import com.abv.bookstore.modules.book.dto.BookRequest;
import com.abv.bookstore.modules.book.dto.BookResponse;
import com.abv.bookstore.modules.book.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/books")
public class BookManagementController extends BaseController<BookRequest, BookResponse,Long> {

    private final BookService bookService;
    public BookManagementController(BookService bookService) {
        super(bookService);
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<BookResponse>>> searchBooks(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int limit,
            @RequestParam(value = "sort", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sort,
            @RequestParam(value = "direction", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String direction

    ) {
        var paginatedResults = bookService.searchAndPaginate(page,limit,sort,direction);
        return ResponseEntity.status(HttpStatus.OK)
                .body(respondPage(paginatedResults,"Books fetched"));
    }
}
