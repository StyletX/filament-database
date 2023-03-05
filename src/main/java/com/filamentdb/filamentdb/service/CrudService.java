package com.filamentdb.filamentdb.service;

import com.filamentdb.filamentdb.internal.StandardEntity;
import com.filamentdb.filamentdb.repository.MyRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class CrudService<REP extends MyRepository<? extends StandardEntity>> {
    private final REP repository;

    CrudService(REP repository) {
        this.repository = repository;
    }

    //    private StandardEntity getEntity(Long id) {
//        return repository.findById(id).orElseThrow(Utils.entityNotFound(String.format("..........")));
//    }
    @Transactional
    public void delete(Long id) {
        repository.softDelete(id);
    }

//    @Transactional
//    public Object getById (Long id) {
//        return repository.getById(id);
//    }
}
