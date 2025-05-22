package com.abv.bookstore.modules.book.service;

import com.abv.bookstore.common.service.BaseService;
import com.abv.bookstore.modules.book.dto.BookFilter;
import com.abv.bookstore.modules.book.dto.BookRequest;
import com.abv.bookstore.modules.book.dto.BookResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService extends BaseService<BookRequest,BookResponse,Long> {
    List<BookResponse> findAllBook();
    Page<BookResponse> searchAndPaginate(BookFilter filter, int page, int size, String sortBy, String sortOrder);
}
