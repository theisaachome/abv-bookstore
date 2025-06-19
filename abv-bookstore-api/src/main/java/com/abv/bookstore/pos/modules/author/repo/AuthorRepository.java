package com.abv.bookstore.pos.modules.author.repo;
import com.abv.bookstore.pos.common.BaseRepository;
import com.abv.bookstore.pos.modules.author.entity.Author;
import java.util.List;

public interface AuthorRepository extends BaseRepository<Author, Long> {
    List<Author> findTop10ByNameContainingIgnoreCase(String keyword);
}
