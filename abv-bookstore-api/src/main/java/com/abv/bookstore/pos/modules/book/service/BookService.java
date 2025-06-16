package com.abv.bookstore.pos.modules.book.service;

import com.abv.bookstore.pos.common.service.BaseService;
import com.abv.bookstore.pos.modules.book.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService extends BaseService<BookReq, BookRes,Long> {
//    Page<BookRes> searchAndPaginate(BookFilter filter, int page, int size, String sortBy, String sortOrder);
Page<BookInventoryDto> searchAndPaginate(BookFilter filter, int page, int size, String sortBy, String sortOrder);

    // seller operation for book-order
    Page<SellerBookDTO> searchBooks(String query, Pageable pageable);     // Seller
//    SellerBookDTO getBookBySku(String sku);            // Seller
    BookPriceResponse addBookPrice(Long bookId,BookPriceRequest request);

}
