package com.abv.bookstore.pos.modules.author.mapper;

import com.abv.bookstore.pos.common.mapper.BaseMapper;
import com.abv.bookstore.pos.common.util.AppCodeGenerator;
import com.abv.bookstore.pos.modules.author.dto.AuthorDto;
import com.abv.bookstore.pos.modules.author.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements BaseMapper<AuthorDto,AuthorDto,Author> {
    @Override
    public Author mapToEntity(AuthorDto request) {
        var authorEntity = new Author();
        authorEntity.setAuthorCode(AppCodeGenerator.generateAuthorRef());
        authorEntity.setName(request.authorName());
        authorEntity.setSlug(AppCodeGenerator.toSlug(request.authorName()));
        authorEntity.setBio(request.bio().isBlank()?"":request.bio());
        return authorEntity;
    }

    @Override
    public AuthorDto mapToResponseDTO(Author author) {
        return new AuthorDto(author.getId(), author.getName(), author.getAuthorCode(), author.getSlug(), author.getBio());
    }

    @Override
    public Author updateEntity(AuthorDto request, Author author) {
        author.setBio(request.bio().isBlank()?"":request.bio());
        author.setName(request.authorName());
        author.setSlug(AppCodeGenerator.toSlug(request.authorName()));
        author.setAuthorCode(author.getAuthorCode());
        return author;
    }
}
