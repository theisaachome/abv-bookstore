package com.abv.bookstore.pos.modules.admin;

import com.abv.bookstore.pos.common.controller.BaseController;
import com.abv.bookstore.pos.common.controller.ResponseHelper;
import com.abv.bookstore.pos.common.domain.ApiResponse;
import com.abv.bookstore.pos.common.domain.PageResponse;
import com.abv.bookstore.pos.modules.author.dto.AuthorDto;
import com.abv.bookstore.pos.modules.author.service.AuthorService;
import com.abv.bookstore.pos.modules.book.dto.BookInventoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*

| Operation                     | Backend Support                      | Notes                                       |
| ----------------------------- | ------------------------------------ | ------------------------------------------- |
| **Find books by author**      | `GET /authors/{id}/books`            | Use pagination for performance.             |
| **Search authors by name**    | `GET /authors?name=John`             | Use `LIKE` query or full-text search.       |
| **View author bio + books**   | `GET /authors/{id}` + embedded books | Or separate endpoint if book list is large. |
| **POS search book by author** | `GET /books?authorId=1&title=java`   | Add filters for fast lookup.                |
 */
@RestController
@RequestMapping("/api/v1/admin/authors")
public class AuthorController extends BaseController<AuthorDto,AuthorDto,Long> {

    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        super(authorService);
        this.authorService = authorService;
    }

    @GetMapping("/{authorId}/books")
    public ResponseEntity<ApiResponse<PageResponse<BookInventoryDto>>> getAllBookByAuthors(
            @PathVariable("authorId") Long authorId
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseHelper.respondPage(null,"Here we go list of books by given author ID"));
    }
}
