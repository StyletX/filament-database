package com.filamentdb.filamentdb.rest;

import com.filamentdb.filamentdb.model.Manufacturer;
import com.filamentdb.filamentdb.repo.ManufacturerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@RestController
@RequestMapping("manf")
@RequiredArgsConstructor
public class ManufacturerController {
    @Autowired
    private final ManufacturerRepository repository;

    @GetMapping("hello")  // localhost:8080/manf/hello
    public String hello() {
        return "Hello!";
    }

    @GetMapping  // localhost:8080/manf?page=0&size=10&sort=id,desc  //asc
    public Page<Manufacturer> getAll(@RequestParam(required = false) String name,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                     @RequestParam(required = false) LocalDate date,
                                     @PageableDefault(size = 2) Pageable pageable) {
        if (Objects.nonNull(name)) {
            return repository.findAllByNameIgnoreCase(name, pageable);
        }
        if (Objects.nonNull(date)) {
            return repository.findAllByCreateDateTimeBetween(
                    date.atTime(LocalTime.MIN), date.atTime(LocalTime.MAX), pageable);
        }
        return repository.findAll(pageable);
    }

    @PostMapping
    public Manufacturer create(@RequestBody Manufacturer manufacturer) {
        return repository.save(manufacturer);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        Manufacturer byId = getById(id);
        repository.delete(byId);
    }

    @PatchMapping("{id}")
    public Manufacturer update(@PathVariable Long id, @RequestBody Manufacturer patch) {
        Manufacturer manufacturer = getById(id);
        manufacturer.setName(patch.getName());
        manufacturer.setUpdateDateTime(LocalDateTime.now());
        return repository.save(manufacturer);
    }

    @GetMapping("{id}")
    public Manufacturer getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}