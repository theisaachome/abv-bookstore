package com.abv.bookstore.pos.modules.author.mapper;

import com.abv.bookstore.pos.common.mapper.BaseMapper;
import com.abv.bookstore.pos.common.util.AppCodeGenerator;
import com.abv.bookstore.pos.modules.author.dto.AuthorDto;
import com.abv.bookstore.pos.modules.author.entity.Author;

public class AuthorMapper implements BaseMapper<AuthorDto,AuthorDto,Author> {
    @Override
    public Author mapToEntity(AuthorDto request) {
        var authorEntity = new Author();
        authorEntity.setAuthorCode(AppCodeGenerator.generateAuthorRef());
        authorEntity.setSlug(AppCodeGenerator.toSlug(request.authorName()));
        authorEntity.setName(request.authorName());
        authorEntity.setBio(request.bio().isBlank()?"":request.bio());
        return authorEntity;
    }

    @Override
    public AuthorDto mapToResponseDTO(Author author) {
        return new AuthorDto(author.getId(), author.getName(), author.getSlug(), author.getBio());
    }

    @Override
    public Author updateEntity(AuthorDto request, Author author) {
        return null;
    }
}
