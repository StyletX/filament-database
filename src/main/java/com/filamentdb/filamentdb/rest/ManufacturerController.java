package com.filamentdb.filamentdb.rest;

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
import org.springframework.format.annotation.DateTimeFormat;
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
    public Page<Manufacturer> getAll(
            @Parameter(name = "name", description = "Короткое наименование производителя пластика", example = "HTP")
            @RequestParam(required = false) String name,
            @Parameter(name = "fullName", description = "Полное наименование производителя пластика",
                    example = "High Tech Plast")
            @RequestParam(required = false) String fullName,
            @Parameter(name = "city", description = "Город", example = "Владимир")
            @RequestParam(required = false) String city,
            @Parameter(name = "inn", description = "ИНН производителя пластика", example = "1234567890")
            @RequestParam(required = false) String inn,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(name = "creationDate", description = "Дата создания записи в базе", example = "2023-03-08")
            @RequestParam(required = false) LocalDate creationDate,
            @Parameter(hidden = true) @ParameterObject @PageableDefault(page = 0, size = 10, sort = "creationDate",
                    direction = Sort.Direction.ASC) Pageable pageable) {
        return manufacturerService.getall(name, fullName, city, inn, creationDate, pageable);
    }

    @PostMapping
    public Manufacturer create(
            @Parameter(name = "name", description = "Короткое наименование производителя пластика", example = "HTP")
            @RequestParam() String name,
            @Parameter(name = "fullName", description = "Полное наименование производителя пластика",
                    example = "High Tech Plast")
            @RequestParam() String fullName,
            @Parameter(name = "city", description = "Город", example = "Владимир")
            @RequestParam() String city,
            @Parameter(name = "inn", description = "ИНН производителя пластика", example = "1234567890")
            @RequestParam() String inn) {
        //@RequestBody Manufacturer manufacturer)
        return manufacturerService.save(name, fullName, city, inn);
    }

    @DeleteMapping("id")
    public void delete(@Parameter(name = "id", description = "Id производителя пластика", example = "1")
                       @RequestParam("id") Long id) {
        manufacturerService.delete(id);
    }

    @PatchMapping("id")
    public Manufacturer update(
            @Parameter(name = "id", description = "Id производителя пластика", example = "1")
            @RequestParam("id") Long id,
            @Parameter(name = "name", description = "Короткое наименование производителя пластика", example = "HTP")
            @RequestParam(required = false) String name,
            @Parameter(name = "fullName", description = "Полное наименование производителя пластика",
                    example = "High Tech Plast")
            @RequestParam(required = false) String fullName,
            @Parameter(name = "city", description = "Город", example = "Владимир")
            @RequestParam(required = false) String city,
            @Parameter(name = "inn", description = "ИНН производителя пластика", example = "1234567890")
            @RequestParam(required = false) String inn) {
        return manufacturerService.update(id, name, fullName, city, inn);
    }

    @PutMapping("id")
    public Manufacturer replace(
            @Parameter(name = "id", description = "Id производителя пластика", example = "1")
            @RequestParam("id") Long id,
            @Parameter(name = "name", description = "Короткое наименование производителя пластика", example = "HTP")
            @RequestParam() String name,
            @Parameter(name = "fullName", description = "Полное наименование производителя пластика",
                    example = "High Tech Plast")
            @RequestParam() String fullName,
            @Parameter(name = "city", description = "Город", example = "Владимир")
            @RequestParam() String city,
            @Parameter(name = "inn", description = "ИНН производителя пластика", example = "1234567890")
            @RequestParam() String inn) {
        return manufacturerService.update(id, name, fullName, city, inn);
    }

    @GetMapping("getById")
//    public Manufacturer getById(@PathVariable("id") Long id) {
    public Manufacturer getById(@Parameter(name = "id", description = "Id производителя пластика", example = "1")
                                @RequestParam("id") Long id) {
        return manufacturerService.getById(id);
    }
}