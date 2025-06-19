package com.abv.bookstore.pos.modules.stock.entity;

import com.abv.bookstore.pos.common.domain.BaseEntity;
import com.abv.bookstore.pos.common.domain.StockMovementType;
import com.abv.bookstore.pos.modules.book.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stock_movements")
public class StockMovement extends BaseEntity {


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockMovementType stockMovementType;
    @Column(nullable = false)
    private Integer quantity = 0;
    @Column(name = "performed_by")
    private String performedBy;
    @Column(name = "reference")
    private String reference;
    @Column(nullable = false)
    private String reason; // e.g. "Customer purchase", "Stock take adjustment", etc.
    @Column(name = "note")
    private String note;


    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
