package com.abv.bookstore.pos.modules.book.dto;

import lombok.Data;

@Data
public class AdminBookDTO {
    private Long id;
    private String title;
    private String slug;
    private String description;
    private String longDescription;
    private String publisher;
    private String isbn;
    private String author;
    private String sku;
}
