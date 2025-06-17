package com.abv.bookstore.pos.modules.book.repo;
import com.abv.bookstore.pos.modules.book.entity.BookPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookPriceRepository extends JpaRepository<BookPrice, Long> {
//    List<BookPrice> findByBookId(Long bookId);
    @Query(value = """
    SELECT * FROM book_prices WHERE book_Id = :bookId
    """, nativeQuery = true)
    List<BookPrice> findByBookId(@Param("bookId") Long bookId);

    @Query("""
    SELECT bp FROM BookPrice bp
    WHERE bp.book.id = :bookId
      AND bp.deleted = false
      AND bp.bookPriceStatus = com.abv.bookstore.pos.modules.book.entity.BookPriceStatus.ACTIVE
      AND (bp.startDate IS NULL OR bp.startDate <= CURRENT_TIMESTAMP)
      AND (bp.endDate IS NULL OR bp.endDate >= CURRENT_TIMESTAMP)
    ORDER BY bp.createdAt DESC
    """)
    Optional<BookPrice> findCurrentPrice(@Param("bookId") Long bookId);

}
