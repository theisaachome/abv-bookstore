package com.abv.bookstore.pos.modules.book.entity;

import com.abv.bookstore.pos.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name="book_price")
public class BookPrice extends BaseEntity {

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

//    @ManyToOne
//    @JoinColumn(name = "book_id", nullable = false)
//    private Book book;

    @Column(nullable = false,name = "start_date")
    private ZonedDateTime startDate;
    @Column(nullable = false,name = "end_date")
    private ZonedDateTime endDate;
}
