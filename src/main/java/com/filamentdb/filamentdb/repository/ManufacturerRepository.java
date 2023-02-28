package com.filamentdb.filamentdb.repository;

import com.filamentdb.filamentdb.model.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
//public interface ManufacturerRepository extends MyRepository<Manufacturer> {
public interface ManufacturerRepository extends MyRepository<Manufacturer> {
    Page<Manufacturer> findAllByNameIgnoreCase(String name, Pageable pageable);

    Page<Manufacturer> findAllByCreateDateTimeBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);

}