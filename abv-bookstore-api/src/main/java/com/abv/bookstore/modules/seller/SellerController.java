package com.abv.bookstore.modules.seller;
import com.abv.bookstore.common.domain.ApiResponse;
import com.abv.bookstore.common.domain.PageResponse;
import com.abv.bookstore.common.util.AppConstants;
import com.abv.bookstore.modules.book.dto.SellerBookDTO;
import com.abv.bookstore.modules.book.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/seller/books")
public class SellerController  {
    private final BookService bookService;
    public SellerController(BookService bookService) {
        this.bookService = bookService;
    }
    // show a list of product with pagination
    // input search option on the top (frontend)

//    Seller can search: by title, SKU, author, ISBN


//    GET /books?title=java&authorName=Martin&page=0&size=10

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<SellerBookDTO>>> searchBooks(
            @RequestParam(required = false) String searchKeyword,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int limit,
            @RequestParam(value = "sort", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sort,
            @RequestParam(value = "direction", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String direction

    ) {
        var pageable = PageRequest.of(page,limit, Sort.by(sort));
        var paginatedResults = bookService.searchBooks(searchKeyword,pageable);
        paginatedResults.getContent();
        return ResponseEntity.status(HttpStatus.OK)
                .body(respondPage(paginatedResults,"Books fetched"));
    }

    protected <T> ApiResponse<PageResponse<T>> respondPage(Page<T> page, String message) {
        PageResponse<T> pageResponse = new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
        return new ApiResponse<>("success", message, pageResponse, Instant.now());
    }
}
