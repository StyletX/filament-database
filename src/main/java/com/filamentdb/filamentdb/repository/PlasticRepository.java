package com.filamentdb.filamentdb.repository;

import com.filamentdb.filamentdb.model.Plastic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PlasticRepository extends MyRepository<Plastic> {

    @Query(value = "select plastic from Plastic plastic where typeName ilike :typeName", nativeQuery = true)
    List<Plastic> findByTypeNameLike(@Param("typeName") String typeName);

    Page<Plastic> findAllByTypeNameIgnoreCase(String typeName, Pageable pageable);

    Page<Plastic> findAllByCreationDateBetween(LocalDateTime atTime, LocalDateTime atTime1, Pageable pageable);
}
