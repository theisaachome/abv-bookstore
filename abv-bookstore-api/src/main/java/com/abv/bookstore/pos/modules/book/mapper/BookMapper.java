package com.abv.bookstore.pos.modules.book.mapper;
import com.abv.bookstore.pos.common.domain.StockMovementType;
import com.abv.bookstore.pos.common.mapper.BaseMapper;
import com.abv.bookstore.pos.common.util.AppCodeGenerator;
import com.abv.bookstore.pos.common.util.SlugGenerator;
import com.abv.bookstore.pos.modules.book.dto.BookInventoryDto;
import com.abv.bookstore.pos.modules.book.dto.BookReq;
import com.abv.bookstore.pos.modules.book.dto.BookRes;
import com.abv.bookstore.pos.modules.book.dto.SellerBookDTO;
import com.abv.bookstore.pos.modules.book.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements BaseMapper<BookReq, BookRes, Book> {


    @Override
    public Book mapToEntity(BookReq request) {
        var book = new Book();
        book.setTitle(request.title());
        book.setPublisher(request.publisher());
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
    public BookRes mapToResponseDTO(Book book) {
        return new BookRes(
                book.getId(),
                book.getTitle(),
                book.getDescription()) ;
    }

    @Override
    public Book updateEntity(BookReq request, Book existingBook) {
        existingBook.setTitle(request.title());
        existingBook.setPublisher(request.publisher());
        existingBook.setDescription(request.description());
        existingBook.setLongDescription(request.longDescription());
        existingBook.setAuthor(request.author());
        existingBook.setIsbn(request.isbn());
        return existingBook;
    }

    public SellerBookDTO mapToSellerBookDTO(Book book) {
        return new SellerBookDTO(
                book.getId(),
                book.getTitle(),
                book.getSku(),
                book.getAuthor(),
                book.getIsbn(),null,1,"");
    }
    public BookInventoryDto mapToBookInventoryDto(Book book){
        var dto = new BookInventoryDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setSku(book.getSku());
        dto.setPublisher(book.getPublisher());
//        dto.setActive(book.isActive());
        dto.setPrice(book.getCurrentPrice().get().getPrice());
        // Calculate stock
        int stock = book.getStockMovements().stream()
                .mapToInt(movement -> {
                    if (movement.getStockMovementType() == StockMovementType.INBOUND) return movement.getQuantity();
                    if (movement.getStockMovementType() == StockMovementType.OUTBOUND) return -movement.getQuantity();
                    return 0;
                }).sum();
        dto.setStock(stock);
        return dto;
    }
}
