package com.abv.bookstore.pos.modules.author.entity;

import com.abv.bookstore.pos.common.domain.BaseEntity;
import com.abv.bookstore.pos.modules.book.entity.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String authorCode; // for system-reference
    @Column(nullable = false,length = 255)
    private String name;
    @Column(length = 255)
    private String slug;
    @Column(columnDefinition = "TEXT")
    private String bio;
    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();
}
