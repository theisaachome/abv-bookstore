package com.abv.bookstore.pos.modules.book.mapper;

import com.abv.bookstore.pos.common.mapper.BaseMapper;
import com.abv.bookstore.pos.common.util.AppCodeGenerator;
import com.abv.bookstore.pos.common.util.SlugGenerator;
import com.abv.bookstore.pos.modules.book.dto.BookRequest;
import com.abv.bookstore.pos.modules.book.dto.BookResponse;
import com.abv.bookstore.pos.modules.book.dto.SellerBookDTO;
import com.abv.bookstore.pos.modules.book.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements BaseMapper<BookRequest, BookResponse, Book> {


    @Override
    public Book mapToEntity(BookRequest request) {
        var book = new Book();
        book.setTitle(request.title());
        book.setSlug(SlugGenerator.toSlug(request.title()));
        book.setDescription(request.description());
        book.setLongDescription(request.longDescription());
        book.setAuthor(request.author());
        book.setSku(AppCodeGenerator.generateSku());
        book.setPublisher("AVG");
        book.setIsbn(request.isbn());
        return book;
    }

    @Override
    public BookResponse mapToResponseDTO(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getDescription()) ;
    }

    @Override
    public void updateEntity(BookRequest request, Book book) {

    }

    public SellerBookDTO mapToSellerBookDTO(Book book) {
        return new SellerBookDTO(book.getId(), book.getTitle(), book.getSku(), book.getAuthor(), book.getIsbn(),null,1,"");
    }
}
