package com.abv.bookstore.modules.stocks.repo;

import com.abv.bookstore.modules.stocks.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    @Query("""
     SELECT COALESCE(SUM(CASE sm.type WHEN 'INBOUND' THEN sm.quantity WHEN 'OUTBOUND' THEN -sm.quantity ELSE 0 END), 0)
      FROM StockMovement sm WHERE sm.book.id = :bookId
    """)
    int sumStockByBook(@Param("bookId") Long bookId);

}
