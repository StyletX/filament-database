package com.filamentdb.filamentdb.repository;

import com.filamentdb.filamentdb.model.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ColorRepository extends MyRepository<Color> {

    Page<Color> findAllByColorName(String colorName, Pageable pageable);

    Page<Color> findAllByCreationDateBetween(LocalDateTime atTime, LocalDateTime atTime1, Pageable pageable);
}
