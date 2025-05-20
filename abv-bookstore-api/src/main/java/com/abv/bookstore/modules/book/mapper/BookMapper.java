package com.abv.bookstore.modules.book.mapper;

import com.abv.bookstore.common.mapper.BaseMapper;
import com.abv.bookstore.modules.book.dto.BookRequest;
import com.abv.bookstore.modules.book.dto.BookResponse;
import com.abv.bookstore.modules.book.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements BaseMapper<BookRequest, BookResponse, Book> {


    @Override
    public Book mapToEntity(BookRequest request) {
        return null;
    }

    @Override
    public BookResponse mapToResponseDTO(Book book) {
        return null;
    }

    @Override
    public void updateEntity(BookRequest request, Book book) {

    }
}
