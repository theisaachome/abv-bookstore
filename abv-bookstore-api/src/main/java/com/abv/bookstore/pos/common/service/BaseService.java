package com.abv.bookstore.pos.common.service;

public interface BaseService <Req,Res,ID>{
    Res create(Req request);
    Res findById(ID id);
    Res update(ID id, Req request);
    void delete(ID id); // Can be soft-delete or hard-delete
}
