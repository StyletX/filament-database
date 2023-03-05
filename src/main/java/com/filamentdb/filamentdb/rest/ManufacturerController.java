package com.filamentdb.filamentdb.rest;

import com.filamentdb.filamentdb.internal.CustomAnnotations.CreationDate;
import com.filamentdb.filamentdb.internal.CustomAnnotations.ManufacturerAnnotations.*;
import com.filamentdb.filamentdb.internal.CustomAnnotations.ManufacturerAnnotations.ManufacturerId;
import com.filamentdb.filamentdb.model.Manufacturer;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.filamentdb.filamentdb.service.ManufacturerService;

import java.time.LocalDate;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("manufacturers")
@Tag(name = "Контроллер для производителей пластика",
        description = "Контролер для работы с объектами класса Manufacturer")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @GetMapping  // localhost:8080/manufacturers?page=0&size=10&sort=id,desc  //asc
    @PageableAsQueryParam
    public Page<Manufacturer> getAll(@ManufacturerName @RequestParam(required = false) String name,
                                     @ManufacturerFullName @RequestParam(required = false) String fullName,
                                     @ManufacturerCity @RequestParam(required = false) String city,
                                     @ManufacturerInn @RequestParam(required = false) Long inn,
                                     @CreationDate @RequestParam(required = false) LocalDate creationDate,
                                     @Parameter(hidden = true) @ParameterObject @PageableDefault(page = 0, size = 10,
                                             sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable) {
        return manufacturerService.getall(name, fullName, city, inn, creationDate, pageable);
    }

    @PostMapping
    public Manufacturer create(@ManufacturerName @RequestParam() String name,
                               @ManufacturerFullName @RequestParam() String fullName,
                               @ManufacturerCity @RequestParam() String city,
                               @ManufacturerInn @RequestParam() Long inn) {
        return manufacturerService.save(name, fullName, city, inn);
    }

    @DeleteMapping("id")
    public void delete(@ManufacturerId @RequestParam("manufacturerId") Long id) {
        manufacturerService.delete(id);
    }

    @PatchMapping("id")
    public Manufacturer update(@ManufacturerId @RequestParam("manufacturerId") Long id,
                               @ManufacturerName @RequestParam(required = false) String name,
                               @ManufacturerFullName @RequestParam(required = false) String fullName,
                               @ManufacturerCity @RequestParam(required = false) String city,
                               @ManufacturerInn @RequestParam(required = false) Long inn) {
        return manufacturerService.update(id, name, fullName, city, inn);
    }

    @PutMapping("id")
    public Manufacturer replace(@ManufacturerId @RequestParam("manufacturerId") Long id,
                                @ManufacturerName @RequestParam() String name,
                                @ManufacturerFullName @RequestParam() String fullName,
                                @ManufacturerCity @RequestParam() String city,
                                @ManufacturerInn @RequestParam() Long inn) {
        return manufacturerService.update(id, name, fullName, city, inn);
    }

    @GetMapping("getById")
    public Manufacturer getById(@ManufacturerId @RequestParam("manufacturerId") Long id) {
        return manufacturerService.getById(id);
    }
}