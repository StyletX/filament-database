package com.filamentdb.filamentdb.repo;

import com.filamentdb.filamentdb.model.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Page<Manufacturer> findAllByNameIgnoreCase(String name, Pageable pageable);

    Page<Manufacturer> findAllByCreateDateTimeBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);

}
