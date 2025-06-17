package com.abv.bookstore.pos.modules.book.service;

import com.abv.bookstore.pos.common.exception.ResourceNotFoundException;
import com.abv.bookstore.pos.modules.book.dto.BookPriceReq;
import com.abv.bookstore.pos.modules.book.dto.BookPriceRes;
import com.abv.bookstore.pos.modules.book.entity.BookPrice;
import com.abv.bookstore.pos.modules.book.entity.BookPriceStatus;
import com.abv.bookstore.pos.modules.book.repo.BookPriceRepository;
import com.abv.bookstore.pos.modules.book.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookPriceServiceImpl implements BookPriceService{
    private final BookPriceRepository bookPriceRepository;
    private final BookRepository bookRepository;

    public BookPriceServiceImpl(BookPriceRepository bookPriceRepository, BookRepository bookRepository) {
        this.bookPriceRepository = bookPriceRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookPriceRes addBookPrice(Long bookId, BookPriceReq request) {
        // check for book to add price
        var book = bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("No book fond"));
        var bookPrice = new BookPrice();
        bookPrice.setPriceType(request.priceType());
        bookPrice.setPrice(request.price());
        bookPrice.setStartDate(request.startDate());
        bookPrice.setEndDate(request.endDate());
        bookPrice.setBook(book);
//        book.addPrice(bookPrice); // optional
        var bookPriceSaved = bookPriceRepository.save(bookPrice);

        return new BookPriceRes(
                bookPriceSaved.getId(),
                bookPriceSaved.getPrice(),
                bookPriceSaved.getPriceType(),
                bookPriceSaved.getStartDate(),
                bookPriceSaved.getEndDate(),
                bookPriceSaved.getBookPriceStatus(),
                isExpired(bookPriceSaved),
                isCurrentPrice(bookPriceSaved));
    }

    @Override
    public BookPriceRes getCurrentPrice(Long bookId) {
        return null;
    }

    @Override
    public List<BookPriceRes> getHistoricalPrice(Long bookId) {
        // book-prices by book-id
        var bookPrices = bookPriceRepository.findByBookId(bookId);
        var currentPrice = bookPrices
                .stream()
                .filter(this::isCurrentPrice)
                .max(Comparator.comparing(BookPrice::getCreatedAt))
                .orElse(null);
       return bookPrices.stream()
                .map(data-> new BookPriceRes(
                        data.getId(),
                        data.getPrice(),
                        data.getPriceType(),
                        data.getStartDate(),
                        data.getEndDate(),
                        data.getBookPriceStatus(),
                        isExpired(data),
                        data.equals(currentPrice)
                )).collect(Collectors.toList());
    }

    @Override
    public BookPriceRes updateBookPrice(Long bookId, Long priceId,BookPriceReq request) {
       var  bookPrice = bookPriceRepository.findById(priceId)
               .orElseThrow(()->new ResourceNotFoundException("No book price fond"));
       bookPrice.setStartDate(request.startDate());
       bookPrice.setPrice(request.price());
       bookPrice.setPriceType(request.priceType());
       bookPrice.setEndDate(request.endDate());
       var bookPriceSaved=  bookPriceRepository.save(bookPrice);
        return new BookPriceRes(
                bookPriceSaved.getId(),
                bookPriceSaved.getPrice(),
                bookPriceSaved.getPriceType(),
                bookPriceSaved.getStartDate(),
                bookPriceSaved.getEndDate(),
                bookPriceSaved.getBookPriceStatus(),
                isExpired(bookPriceSaved),
                isCurrentPrice(bookPriceSaved));
    }

    @Override
    public String deleteBookPrice(Long bookId,Long priceId) {
        var  bookPrice = bookPriceRepository.findById(priceId)
                .orElseThrow(()->new ResourceNotFoundException("No book price fond"));
        bookPrice.setDeleted(Boolean.TRUE);
        bookPrice.setBookPriceStatus(BookPriceStatus.DELETED);
        bookPrice.setEndDate(LocalDateTime.now());
        bookPriceRepository.save(bookPrice);
        return "Successfully deleted Book Price for : " + bookId;
    }

    private  boolean isExpired(BookPrice bookPrice){
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        return bookPrice.getBookPriceStatus() == BookPriceStatus.EXPIRED
                || (bookPrice.getEndDate() != null && bookPrice.getEndDate().isBefore(now));
    }
    private boolean isCurrentPrice(BookPrice bookPrice){
        if (bookPrice.isDeleted()) {
            return false;
        }

        if (bookPrice.getBookPriceStatus() == BookPriceStatus.EXPIRED) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);

        boolean afterStart = bookPrice.getStartDate() == null || !now.isBefore(bookPrice.getStartDate());
        boolean beforeEnd = bookPrice.getEndDate() == null || !now.isAfter(bookPrice.getEndDate());

        return afterStart && beforeEnd;
    }
}

