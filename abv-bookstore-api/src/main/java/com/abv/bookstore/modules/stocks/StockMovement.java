package com.abv.bookstore.modules.stocks;

import com.abv.bookstore.common.domain.BaseEntity;
import com.abv.bookstore.common.domain.Type;
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

//    @ManyToOne
//    @JoinColumn(name = "book_id", nullable = false)
//    private Book book;
    @Column(nullable = false)
    private String reason; // e.g. "Customer purchase", "Stock take adjustment", etc.

}
