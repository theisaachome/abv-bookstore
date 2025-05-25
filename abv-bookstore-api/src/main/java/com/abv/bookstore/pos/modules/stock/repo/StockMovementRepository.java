package com.abv.bookstore.pos.modules.stock.repo;

import com.abv.bookstore.pos.common.BaseRepository;
import com.abv.bookstore.pos.modules.stock.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockMovementRepository extends BaseRepository<StockMovement, Long> {
//    @Query("""
//     SELECT COALESCE(SUM(CASE sm.stockMovementType WHEN 'INBOUND' THEN sm.quantity WHEN 'OUTBOUND' THEN -sm.quantity ELSE 0 END), 0)
//      FROM StockMovement sm WHERE sm.book.id = :bookId
//    """)
//    int sumStockByBook(@Param("bookId") Long bookId);

    @Query("""
     SELECT COALESCE(SUM(sm.quantity), 0)
      FROM StockMovement sm WHERE sm.book.id = :bookId
    """)
    int sumStockByBook(@Param("bookId") Long bookId);
}
