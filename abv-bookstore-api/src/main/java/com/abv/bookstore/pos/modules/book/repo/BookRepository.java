package com.abv.bookstore.pos.modules.book.repo;
import com.abv.bookstore.pos.common.BaseRepository;
import com.abv.bookstore.pos.modules.book.entity.Book;
import com.abv.bookstore.pos.modules.stock.dto.BookStockView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends BaseRepository<Book, Long> {

//    @Query("""
//      SELECT new com.example.dto.BookStockView(
//         b.id, b.title, b.sku, b.price,
//         SUM(CASE WHEN sm.type = 'IN' THEN sm.quantity ELSE -sm.quantity END)
//      )
//      FROM Book b
//      LEFT JOIN StockMovement sm ON sm.book = b
//      GROUP BY b.id
//    """,
//            countQuery = """
//    SELECT COUNT(b.id)
//    FROM Book b
//    """
//    )
//    Page<BookStockView> findBooksWithStock(Pageable pageable);

    @EntityGraph(attributePaths = {"prices", "stockMovements"})
    Page<Book> findAll(Specification<Book> spec, Pageable pageable);


}
