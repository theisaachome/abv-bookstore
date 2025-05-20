package com.abv.bookstore.common.controller;

import com.abv.bookstore.common.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseController <Req,Res,ID>{

    protected final BaseService<Req, Res, ID> service;

    protected BaseController(BaseService<Req, Res, ID> service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Res> create(@RequestBody  Req request) {
        var response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Res> getById(@PathVariable ID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Res> update(@PathVariable ID id, @RequestBody Req request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
