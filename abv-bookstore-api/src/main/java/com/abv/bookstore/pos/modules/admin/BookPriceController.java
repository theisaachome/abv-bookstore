package com.abv.bookstore.pos.modules.admin;
import com.abv.bookstore.pos.common.domain.ApiResponse;
import com.abv.bookstore.pos.common.domain.PageResponse;
import com.abv.bookstore.pos.modules.book.dto.BookPriceReq;
import com.abv.bookstore.pos.modules.book.dto.BookPriceRes;
import com.abv.bookstore.pos.modules.book.service.BookPriceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/admin/books/")
public class BookPriceController {
    // add/edit/delete book prices

    private final BookPriceService bookPriceService;

    public BookPriceController(BookPriceService bookPriceService) {
        this.bookPriceService = bookPriceService;
    }

    //`POST /books/{bookId}/prices`
    @PostMapping("/{bookId}/prices")
    public ResponseEntity<ApiResponse<BookPriceRes>> addBookPrices(@PathVariable("bookId")Long bookId,
                                                                       @RequestBody @Valid BookPriceReq request){
        var result = bookPriceService.addBookPrice(bookId,request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("new book price has been added successfully.",result));
    }
    @PutMapping("/{bookId}/prices/{priceId}")
    public ResponseEntity<ApiResponse<BookPriceRes>> updateBookPrice(
            @PathVariable("bookId") Long bookId,@PathVariable("priceId") Long priceId ,
            @RequestBody @Valid BookPriceReq request
    ){
        var result = bookPriceService.updateBookPrice(bookId,priceId,request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("new book price has been added successfully.",result));
    }
    @DeleteMapping("/{bookId}/prices/{priceId}")
    public ResponseEntity<ApiResponse<String>> deleteBookPrice(@PathVariable("bookId")Long bookId,
                                                  @PathVariable("priceId") Long priceId){
        var result = bookPriceService.deleteBookPrice(bookId,priceId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("book price has been deleted successfully.",result));
    }



    @GetMapping("/{bookId}/prices")
    public ResponseEntity<ApiResponse<List<BookPriceRes>>> getBookPrices(@PathVariable("bookId") Long bookId){
         var result = bookPriceService.getHistoricalPrice(bookId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Fetched Book Prices",result));
    }

}



/*

| Operation         | Purpose                           | Endpoint Example                          |
| ----------------- | --------------------------------- | ----------------------------------------- |
| Add new price     | Add discount/promotion/clearance  | `POST /books/{bookId}/prices`             |
| Get current price | Show user current effective price | `GET /books/{bookId}/current-price`       |
| List all prices   | View history or types             | `GET /books/{bookId}/prices`              |
| Update price      | Correct or adjust a price         | `PUT /books/{bookId}/prices/{priceId}`    |
| Delete price      | Remove/expire a price             | `DELETE /books/{bookId}/prices/{priceId}` |


✅ What Book Price Operations You Typically Need
Here are basic and necessary operations for real-world book pricing logic:

1. Add New Price
Already implemented — good.

But consider renaming the endpoint to:

http
        Copy
Edit
POST /books/{bookId}/prices
With a field in the request like:

json
        Copy
Edit
{
        "priceType": "PROMOTION",
        "price": 25.99,
        "startAt": "...",
        "endAt": "..."
}
2. Get Current Price
A method to get the active price at the current time.

        Endpoint:

http
        Copy
Edit
GET /books/{bookId}/current-price
This should:

Look for a BookPrice with:

startAt <= now && (endAt is null || endAt >= now)

Ordered by priority (e.g., PROMOTION overrides DEFAULT)

        If none, fallback to DEFAULT or raise an exception

3. List Price History
Allow user/admin to view the price history for a book.

Endpoint:

http
        Copy
Edit
GET /books/{bookId}/prices
Returns all BookPrice records, optionally with pagination or filters:

filter by price type (e.g., ?type=PROMOTION)

filter by date range

4. Delete or Deactivate Price
Allow deletion or soft-deactivation of a price record.

        Options:

Soft delete: Set endAt to now

Hard delete: Remove from DB (only if not used in an order)

Endpoint:

http
        Copy
Edit
DELETE /books/{bookId}/prices/{priceId}
        5. Update a Price Entry
In case of a mistake or admin override.

Endpoint:

h
        Copy
Edit
PUT /books/{bookId}/prices/{priceId}
Fields that can be updated:

price value

date range

price type



 */
