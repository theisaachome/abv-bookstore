package com.abv.bookstore.modules.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerBookDTO {
    private Long id;
    private String title;
    private String sku;
    private String author;
    private String isbn;
    private BigDecimal price;
    private Integer stockQuantity;
    private String coverImageUrl; // Optional for UI
}
