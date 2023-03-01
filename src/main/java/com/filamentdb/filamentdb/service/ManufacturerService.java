package com.filamentdb.filamentdb.service;

import com.filamentdb.filamentdb.model.Manufacturer;
import com.filamentdb.filamentdb.repository.ManufacturerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

//    public String hello() {
//        return "Hello";
//    }

    public Page<Manufacturer> getall(String name, LocalDate date, Pageable pageable) {
        if (Objects.nonNull(name)) {
            return manufacturerRepository.findAllByNameIgnoreCase(name, pageable);
        }
        if (Objects.nonNull(date)) {
            return manufacturerRepository.findAllByCreationDateBetween(
                    date.atTime(LocalTime.MIN), date.atTime(LocalTime.MAX), pageable);
        }
        return manufacturerRepository.findAll(pageable);
    }

    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public void delete(Long id) {
        Manufacturer byId = getById(id);
        manufacturerRepository.delete(byId);
    }

    public Manufacturer getById(Long id) {
        return manufacturerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Manufacturer update(Long id, Manufacturer patch) {
        Manufacturer manufacturer = getById(id);
        manufacturer.setName(patch.getName());

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//        manufacturer.setUpdateDateTime(LocalDateTime.now());
        return manufacturerRepository.save(manufacturer);
    }
}
