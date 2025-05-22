package com.abv.bookstore.modules.orders.dt;

public record OrderRequest(
        Long bookId,
        Integer quantity
) {
}
