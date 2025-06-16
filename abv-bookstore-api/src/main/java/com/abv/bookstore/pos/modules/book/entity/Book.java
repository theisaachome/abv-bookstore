package com.abv.bookstore.pos.modules.book.entity;
import com.abv.bookstore.pos.common.domain.BaseEntity;
import com.abv.bookstore.pos.modules.stock.entity.StockMovement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

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

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private Set<StockMovement> stockMovements = new HashSet<>();

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookPrice> prices= new HashSet<>();

    public Optional<BookPrice> getCurrentPrice() {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        return prices.stream()
                .filter(p -> (p.getStartDate() == null || !now.isBefore(p.getStartDate()))
                        &&
                        (p.getEndDate() == null || !now.isAfter(p.getEndDate())))
                .max(Comparator.comparing(BookPrice::getCreatedAt));
    }

    public void addPrice(BookPrice bookPrice) {
        this.prices.add(bookPrice);
        bookPrice.setBook(this);
    }

    public void removePrice(BookPrice bookPrice) {
        bookPrice.setBook(null);
        this.prices.remove(bookPrice);
    }
    public void removeAllPrices() {
        Iterator<BookPrice> iterator = prices.iterator();
        while (iterator.hasNext()) {
            BookPrice bookPrice = iterator.next();
            bookPrice.setBook(null);
            iterator.remove();
        }
    }


}
