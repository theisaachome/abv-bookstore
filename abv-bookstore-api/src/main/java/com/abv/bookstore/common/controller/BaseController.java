package com.abv.bookstore.common.controller;

import com.abv.bookstore.common.domain.ApiResponse;
import com.abv.bookstore.common.service.BaseService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;


public abstract class BaseController <Req,Res,ID>{

    protected final BaseService<Req, Res, ID> service;

    protected BaseController(BaseService<Req, Res, ID> service) {
        this.service = service;
    }

    protected <T> ApiResponse<T> respond(T data,String message) {
        return  new ApiResponse<>("success",message,data, Instant.now());
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Res>> create(@RequestBody @Valid Req request) {
        var response = service.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respond(response,"Created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Res> getById(@PathVariable ID id) {
        var result = service.findById(id);
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Res> update(@PathVariable ID id, @RequestBody @Valid Req request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
