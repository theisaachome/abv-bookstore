package com.abv.bookstore.pos.modules.author.service;
import com.abv.bookstore.pos.common.service.BaseService;
import com.abv.bookstore.pos.modules.author.dto.AuthorDto;
import com.abv.bookstore.pos.modules.author.dto.AuthorFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface AuthorService extends BaseService<AuthorDto,AuthorDto,Long> {
   Page<AuthorDto> searchAuthor(AuthorFilter keyword, int page, int size, String sortBy, String sortOrder);
   List<AuthorDto> suggestAuthors(String keyword);
}
