package com.abv.bookstore.pos.common.controller;
import com.abv.bookstore.pos.common.domain.ApiResponse;
import com.abv.bookstore.pos.common.domain.PageResponse;
import org.springframework.data.domain.Page;

import java.time.Instant;

public class ResponseHelper {

    // json formatted a resource response
    public static  <T> ApiResponse<T> respond(T data,String message) {
        return  new ApiResponse<>("success",message,data, Instant.now());
    }

    // json formatted list of resource response
    public static  <T> ApiResponse<PageResponse<T>> respondPage(Page<T> page, String message) {
        PageResponse<T> pageResponse = new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
        return new ApiResponse<>("success", message, pageResponse, Instant.now());
    }

}
