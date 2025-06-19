package com.abv.bookstore.pos.modules.author.service;

import com.abv.bookstore.pos.common.BaseRepository;
import com.abv.bookstore.pos.common.mapper.BaseMapper;
import com.abv.bookstore.pos.common.service.BaseServiceImpl;
import com.abv.bookstore.pos.modules.author.dto.AuthorDto;
import com.abv.bookstore.pos.modules.author.entity.Author;
import com.abv.bookstore.pos.modules.author.mapper.AuthorMapper;
import com.abv.bookstore.pos.modules.author.repo.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends BaseServiceImpl<AuthorDto,AuthorDto,Long,Author> implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        super(authorRepository, authorMapper, Author.class, AuthorDto.class);
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }
}
