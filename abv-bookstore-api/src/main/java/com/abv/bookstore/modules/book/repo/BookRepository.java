package com.abv.bookstore.modules.book.repo;

import com.abv.bookstore.modules.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
