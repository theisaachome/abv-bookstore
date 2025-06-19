package com.abv.bookstore.pos.modules.stock.repo;

import com.abv.bookstore.pos.common.BaseRepository;
import com.abv.bookstore.pos.common.domain.StockMovementType;
import com.abv.bookstore.pos.modules.stock.dto.StockMovementDtoView;
import com.abv.bookstore.pos.modules.stock.entity.StockMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @EntityGraph(attributePaths = {"book"})
    Page<StockMovement> findAll(Specification spec, Pageable pageable);
}
