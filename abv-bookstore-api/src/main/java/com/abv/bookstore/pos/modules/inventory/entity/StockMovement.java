package com.abv.bookstore.pos.modules.inventory.entity;

import com.abv.bookstore.pos.common.domain.BaseEntity;
import com.abv.bookstore.pos.common.domain.Type;
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
    private Type type;

    @Column(nullable = false)
    private Integer quantity = 0;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @Column(nullable = false)
    private String reason; // e.g. "Customer purchase", "Stock take adjustment", etc.

}
