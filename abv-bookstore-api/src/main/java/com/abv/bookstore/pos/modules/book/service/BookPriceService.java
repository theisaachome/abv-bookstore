package com.abv.bookstore.pos.modules.book.service;

import com.abv.bookstore.pos.modules.book.dto.BookPriceReq;
import com.abv.bookstore.pos.modules.book.dto.BookPriceRes;

import java.util.List;

public interface BookPriceService {

    BookPriceRes addBookPrice(Long bookId, BookPriceReq request);
    BookPriceRes getCurrentPrice(Long bookId);
    List<BookPriceRes> getHistoricalPrice(Long bookId);
    BookPriceRes  updateBookPrice(Long bookId, Long priceId,BookPriceReq request);
    String deleteBookPrice(Long bookId, Long priceId);
}
