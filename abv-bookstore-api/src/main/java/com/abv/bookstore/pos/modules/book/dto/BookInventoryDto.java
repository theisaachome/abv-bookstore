package com.abv.bookstore.pos.modules.book.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookInventoryDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String sku;
    private String publisher;
    private BigDecimal price;
    private BigDecimal promotionPrice;
    private Integer stock;
    private boolean active;
}
