package com.abv.bookstore.modules.book.entity;

import com.abv.bookstore.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String title;

    @Column( length = 255)
    private String publisher;

    @Column(nullable = false, length = 20,unique = true)
    private String isbn;

    @Column(length = 255)
    private String author; // If using `author_id`, remove this

    @Column(length = 255,unique = true,nullable = false)
    private String sku; // Unique code or SKU for the book

    @OneToMany
    private List<BookPrice> prices= new ArrayList<>();

    public Optional<BookPrice> getCurrentPrice() {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        return prices.stream()
                .filter(p -> (p.getStartDate() == null || !now.isBefore(p.getStartDate())) &&
                        (p.getEndDate() == null || !now.isAfter(p.getEndDate())))
                .max(Comparator.comparing(BookPrice::getCreatedAt));
    }

}
