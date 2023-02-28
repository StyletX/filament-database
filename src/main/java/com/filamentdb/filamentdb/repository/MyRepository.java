package com.filamentdb.filamentdb.repository;

import com.filamentdb.filamentdb.internal.IdEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface MyRepository<T extends IdEntity<Long>> extends PagingAndSortingRepository<T, Long> {
    @Modifying
    @Query("update #{#entityName} e set e.deleted=true where e.id=?1")
    void softDelete(Long id);
}
