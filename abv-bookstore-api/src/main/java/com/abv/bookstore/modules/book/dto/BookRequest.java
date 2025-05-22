package com.abv.bookstore.modules.book.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record BookRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Author is required")
        String author,


        @NotNull(message = "Book Description is required")
        @NotBlank(message = "Description is required")
        @Size(max = 1000)
        String description,

        String longDescription,

//        @Pattern(regexp = "\\d{13}", message = "ISBN must be a 13-digit number")
        String isbn,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
        BigDecimal price,

        @NotNull(message = "Initial stock is required")
        @Min(value = 0, message = "Stock must be zero or more")
        Integer initialStockQuantity
) {
}
