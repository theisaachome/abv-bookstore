package com.abv.bookstore.modules.admin;

import com.abv.bookstore.modules.book.dto.BookRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/books")
public class BookManagementController {

    // administrative operations such as create, update, delete, and view books

    @PostMapping
    public ResponseEntity<String> addNewBook(@RequestBody BookRequest request){

        return  new ResponseEntity<>("New Book recorded", HttpStatus.OK);
    }
    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable("bookId")Long bookId, @RequestBody BookRequest request){

        return  new ResponseEntity<>("Updated Book recorded", HttpStatus.OK);
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId")Long bookId){
        return new ResponseEntity<>("Deleted Book recorded", HttpStatus.OK);
    }


}
