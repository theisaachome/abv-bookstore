package com.abv.bookstore.pos.modules.book.entity;
import com.abv.bookstore.pos.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 255)
    private String slug;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String longDescription;

    @Column( length = 255)
    private String publisher;

    @Column( length = 20,unique = true)
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
