package com.abv.bookstore.pos.common.controller;
import com.abv.bookstore.pos.common.domain.ApiResponse;
import com.abv.bookstore.pos.common.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public abstract class BaseController <Req,Res,ID>{

    protected final BaseService<Req, Res, ID> service;

    protected BaseController(BaseService<Req, Res, ID> service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Res>> create(@RequestBody @Valid Req request) {
        var response = service.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseHelper.respond(response,"Created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Res>> getById(@PathVariable ID id) {
        var result = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseHelper.respond(result,"Found successfully"));
    }

    // standard resource update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Res>> update(@PathVariable ID id, @RequestBody @Valid Req request) {
        var result = service.update(id, request);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseHelper.respond(result,"Updated successfully"));
    }

    // soft delete for resource (no-physical delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
