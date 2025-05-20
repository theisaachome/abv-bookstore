package com.abv.bookstore.modules.book.service;

import com.abv.bookstore.common.service.BaseServiceImpl;
import com.abv.bookstore.modules.book.dto.BookRequest;
import com.abv.bookstore.modules.book.dto.BookResponse;
import com.abv.bookstore.modules.book.entity.Book;
import com.abv.bookstore.modules.book.mapper.BookMapper;
import com.abv.bookstore.modules.book.repo.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends BaseServiceImpl<BookRequest,BookResponse,Long, Book>
        implements BookService{

    public BookServiceImpl(BookRepository repository, BookMapper mapper) {
        super(repository,mapper,Book.class,BookResponse.class);
    }
}
