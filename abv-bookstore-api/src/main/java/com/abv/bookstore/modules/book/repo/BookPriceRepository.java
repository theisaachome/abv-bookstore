package com.abv.bookstore.modules.book.repo;
import com.abv.bookstore.modules.book.entity.BookPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPriceRepository extends JpaRepository<BookPrice, Long> {
}
