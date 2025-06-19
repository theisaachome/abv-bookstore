package com.abv.bookstore.pos.modules.admin;

import com.abv.bookstore.pos.common.controller.BaseController;
import com.abv.bookstore.pos.common.controller.ResponseHelper;
import com.abv.bookstore.pos.common.domain.ApiResponse;
import com.abv.bookstore.pos.common.domain.PageResponse;
import com.abv.bookstore.pos.common.util.AppConstants;
import com.abv.bookstore.pos.modules.author.dto.AuthorDto;
import com.abv.bookstore.pos.modules.author.dto.AuthorFilter;
import com.abv.bookstore.pos.modules.author.service.AuthorService;
import com.abv.bookstore.pos.modules.book.dto.BookFilter;
import com.abv.bookstore.pos.modules.book.dto.BookInventoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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


    //    GET /authors?code=java&name=Martin&page=0&size=10
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AuthorDto>>> searchAuthors(
            @ModelAttribute AuthorFilter filter,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int limit,
            @RequestParam(value = "sort", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sort,
            @RequestParam(value = "direction", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false)
            String direction
    ) {
        var paginatedResults = authorService.searchAuthor(filter,page,limit,sort,direction);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseHelper.respondPage(paginatedResults,"author fetched"));
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<AuthorDto>> suggestAuthors(@RequestParam(value = "keyword",required = false)String keyword){
        var resultList = authorService.suggestAuthors(keyword);
        return ResponseEntity.status(HttpStatus.OK)
                .body(resultList);
    }
}
