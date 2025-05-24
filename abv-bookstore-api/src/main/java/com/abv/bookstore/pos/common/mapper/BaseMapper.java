package com.abv.bookstore.pos.common.mapper;

public interface BaseMapper <Req,Res,Entity>{
    Entity mapToEntity(Req request);
    Res mapToResponseDTO(Entity entity);
    void updateEntity(Req request, Entity entity);
}
