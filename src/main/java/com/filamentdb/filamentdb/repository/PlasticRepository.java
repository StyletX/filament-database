package com.filamentdb.filamentdb.repository;

import com.filamentdb.filamentdb.model.Plastic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlasticRepository extends MyRepository<Plastic> {

    @Query(value = "select plastic from Plastic plastic where name ilike :name", nativeQuery = true)
    List<Plastic> finfByNameLike(@Param("name") String name);
}
