package com.abv.bookstore.pos.common.service;

import com.abv.bookstore.pos.common.BaseRepository;
import com.abv.bookstore.pos.common.exception.ResourceNotFoundException;
import com.abv.bookstore.pos.common.mapper.BaseMapper;

public class BaseServiceImpl <NewReq,Res,ID,Entity> implements BaseService<NewReq,Res,ID> {

    protected final BaseRepository<Entity,ID> repository;
    protected final BaseMapper<NewReq,Res,Entity> baseMapper;
    private Class<Entity> entityClass;
    private Class<Res> resourceClass;

    public BaseServiceImpl(BaseRepository<Entity, ID> repository,
                           BaseMapper<NewReq, Res, Entity> baseMapper,
                           Class<Entity> entityClass, Class<Res> resourceClass) {
        this.repository = repository;
        this.baseMapper = baseMapper;
        this.entityClass = entityClass;
        this.resourceClass = resourceClass;
    }

    @Override
    public Res create(NewReq request) {
        var entity = baseMapper.mapToEntity(request);
        var saved =repository.save(entity);
        return  baseMapper.mapToResponseDTO(saved);
    }

    @Override
    public Res findById(ID id) {
        var entity = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Resource","ID", (Long) id));
        return baseMapper.mapToResponseDTO(entity);
    }

    @Override
    public Res update(ID id, NewReq request) {
        var entity = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Resource","ID", (Long) id));
       var updated = baseMapper.updateEntity(request, entity);
        return baseMapper.mapToResponseDTO(repository.save(updated));
    }

    @Override
    public void delete(ID id) {

    }
}
