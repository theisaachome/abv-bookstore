package com.abv.bookstore.pos.modules.book.repo;
import com.abv.bookstore.pos.modules.book.entity.BookPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPriceRepository extends JpaRepository<BookPrice, Long> {
}
