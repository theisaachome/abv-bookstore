package com.abv.bookstore.pos.modules.author.service;
import com.abv.bookstore.pos.common.service.BaseServiceImpl;
import com.abv.bookstore.pos.common.service.BaseSpecification;
import com.abv.bookstore.pos.common.service.SearchCriteria;
import com.abv.bookstore.pos.common.service.SearchOperation;
import com.abv.bookstore.pos.modules.author.dto.AuthorDto;
import com.abv.bookstore.pos.modules.author.dto.AuthorFilter;
import com.abv.bookstore.pos.modules.author.entity.Author;
import com.abv.bookstore.pos.modules.author.mapper.AuthorMapper;
import com.abv.bookstore.pos.modules.author.repo.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl extends BaseServiceImpl<AuthorDto,AuthorDto,Long,Author> implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        super(authorRepository, authorMapper, Author.class, AuthorDto.class);
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public Page<AuthorDto> searchAuthor(AuthorFilter keyword, int page, int size, String sortBy, String sortOrder) {
        Sort sortDir = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        var pageable = PageRequest.of(page, size, sortDir);

        // specification build-up
        BaseSpecification<Author> specification = new BaseSpecification<Author>() ;
        if(keyword.code() !=null){
            specification.add(new SearchCriteria("authorCode",keyword.code(), SearchOperation.LIKE));
        }
        if(keyword.name() !=null){
            specification.add(new SearchCriteria("name",keyword.name(), SearchOperation.LIKE));
        }
        var authorPages = authorRepository.findAll(specification, pageable);
        return authorPages.map(authorMapper::mapToResponseDTO);
    }

    @Override
    public List<AuthorDto> suggestAuthors(String keyword) {
//        var pageable = PageRequest.of(0,10);
        String trimmed = keyword.trim();
        if (trimmed.startsWith("@")) {
            String nameQuery = trimmed.substring(1); // remove '@'
         return authorRepository.findTop10ByNameContainingIgnoreCase(nameQuery)
                    .stream().map(authorMapper::mapToResponseDTO)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
