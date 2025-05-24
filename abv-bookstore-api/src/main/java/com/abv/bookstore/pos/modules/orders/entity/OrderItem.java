package com.abv.bookstore.pos.modules.orders.entity;

import com.abv.bookstore.pos.common.domain.BaseEntity;
import com.abv.bookstore.pos.modules.book.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;

import java.math.BigDecimal;

@BatchSize(size = 20)
@Setter
@Getter
@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice; // Price at time of sale (important for price history)

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal lineTotal; // unitPrice * quantity

    @Column(length = 255)
    private String bookTitle; // Snapshot of book title at time of sale

//    @Column(length = 20)
//    private String bookIsbn; // Snapshot of ISBN at time of sale

    @Column(length = 255)
    private String bookSku; // Snapshot of SKU at time of sale

    // Calculate line total automatically
    @PrePersist
    @PreUpdate
    private void calculateLineTotal() {
        if (unitPrice != null && quantity != null) {
            this.lineTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }
}
