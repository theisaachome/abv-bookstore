package com.abv.bookstore.modules.book.mapper;

import com.abv.bookstore.common.mapper.BaseMapper;
import com.abv.bookstore.common.util.SkuGenerator;
import com.abv.bookstore.modules.book.dto.BookRequest;
import com.abv.bookstore.modules.book.dto.BookResponse;
import com.abv.bookstore.modules.book.entity.Book;
import com.abv.bookstore.modules.book.entity.BookPrice;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BookMapper implements BaseMapper<BookRequest, BookResponse, Book> {


    @Override
    public Book mapToEntity(BookRequest request) {
        var book = new Book();
        book.setAuthor(request.author());
        book.setSku(SkuGenerator.generateSku());
        book.setTitle(request.title());
        book.setPublisher("AVG");
        book.setIsbn(request.isbn());
        return book;
    }

    @Override
    public BookResponse mapToResponseDTO(Book book) {
        return null;
    }

    @Override
    public void updateEntity(BookRequest request, Book book) {

    }
}
