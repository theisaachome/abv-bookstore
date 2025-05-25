package com.abv.bookstore.pos.modules.book.entity;

import com.abv.bookstore.pos.common.domain.BaseEntity;
import com.abv.bookstore.pos.common.domain.PriceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name="book_prices")
public class BookPrice extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriceType priceType = PriceType.REGULAR;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false,name = "start_date")
    private ZonedDateTime startDate;
    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Override
    public int hashCode() {
        return 2025;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id != null && id.equals(((BookPrice) obj).id);
    }
}
