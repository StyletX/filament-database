package com.filamentdb.filamentdb.rest;

import com.filamentdb.filamentdb.internal.CustomAnnotations.ColorId;
import com.filamentdb.filamentdb.internal.CustomAnnotations.CreationDate;
import com.filamentdb.filamentdb.internal.CustomAnnotations.ManufacturerAnnotations.ManufacturerId;
import com.filamentdb.filamentdb.internal.CustomAnnotations.PlasticAnnotations.PlasticDiameter;
import com.filamentdb.filamentdb.internal.CustomAnnotations.PlasticAnnotations.PlasticId;
import com.filamentdb.filamentdb.internal.CustomAnnotations.PlasticAnnotations.PlasticTypeName;
import com.filamentdb.filamentdb.model.Plastic;
import com.filamentdb.filamentdb.service.PlasticService;
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

import java.time.LocalDate;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("plastics")
@Tag(name = "Контроллер для пластиков", description = "Контролер для работы с объектами класса Plastic")
public class PlasticController {
    private final PlasticService plasticService;

    @GetMapping  // localhost:8080/plastics?page=0&size=10&sort=id,desc  //asc
    @PageableAsQueryParam
    public Page<Plastic> getAll(@PlasticTypeName @RequestParam(required = false) String typeName,
                                @PlasticDiameter @RequestParam(required = false) Float diameter,
                                @ManufacturerId @RequestParam(required = false) Long manufacturerId,
                                @ColorId @RequestParam(required = false) Long colorId,
                                @CreationDate @RequestParam(required = false) LocalDate creationDate,
                                @Parameter(hidden = true) @ParameterObject @PageableDefault(page = 0, size = 10,
                                        sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable) {
        return plasticService.getall(typeName, diameter, manufacturerId, colorId, creationDate, pageable);
    }

    @PostMapping
    public Plastic create(@PlasticTypeName @RequestParam() String typeName,
                          @PlasticDiameter @RequestParam() Float diameter,
                          @ManufacturerId @RequestParam() Long manufacturerId,
                          @ColorId @RequestParam() Long colorId) {
        return plasticService.save(typeName, diameter, manufacturerId, colorId);
    }

    @DeleteMapping("id")
    public void delete(@PlasticId @RequestParam("id") Long id) {
        plasticService.delete(id);
    }

    @PatchMapping("id")
    public Plastic update(@PlasticId @RequestParam("id") Long id,
                          @PlasticTypeName @RequestParam(required = false) String typeName,
                          @PlasticDiameter @RequestParam(required = false) Float diameter,
                          @ManufacturerId @RequestParam(required = false) Long manufacturerId,
                          @ColorId @RequestParam(required = false) Long colorId) {
        return plasticService.update(id, typeName, diameter, manufacturerId, colorId);
    }

    @PutMapping("id")
    public Plastic replace(@PlasticId @RequestParam("id") Long id,
                           @PlasticTypeName @RequestParam() String typeName,
                           @PlasticDiameter @RequestParam() Float diameter,
                           @ManufacturerId @RequestParam() Long manufacturerId,
                           @ColorId @RequestParam() Long colorId) {
        return plasticService.update(id, typeName, diameter, manufacturerId, colorId);
    }

    @GetMapping("getById")
    public Plastic getById(@PlasticId @RequestParam("id") Long id) {
        return plasticService.getById(id);
    }
}
