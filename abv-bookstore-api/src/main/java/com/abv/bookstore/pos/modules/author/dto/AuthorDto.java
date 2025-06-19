package com.abv.bookstore.pos.modules.author.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthorDto(
        Long id,
        @NotNull(message = "Author name must not be null.")
        @NotBlank(message = "Author name is required.")
        @Size(max = 255, message = "Author name must not exceed 255 characters.")
        String authorName,

        @Size(max = 255, message = "Slug must not exceed 255 characters.")
        String slug,

        @Size(max = 1000, message = "Bio must not exceed 1000 characters.")
        String bio

) {
}
