package com.filamentdb.filamentdb.rest;

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
import org.springframework.format.annotation.DateTimeFormat;
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
    public Page<Plastic> getAll(
            @Parameter(name = "typeName", description = "Имя типа пластика, например 'ABS, PET-G...'", example = "ABS")
            @RequestParam(required = false) String typeName,
            @Parameter(name = "diameter", description = "Диаметр пластика, например '1.75, 3.0...'", example = "1.75")
            @RequestParam(required = false) Float diameter,
            @Parameter(name = "manufacturerId", description = "ID производителя пластика, например '1', '2',...'", example = "0")
            @RequestParam(required = false) Long manufacturerId,
            @Parameter(name = "colorId", description = "ID цвета пластика, например '1', '2',...'", example = "0")
            @RequestParam(required = false) Long colorId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(name = "creationDate", description = "Дата создания объекта", example = "2023-03-08")
            @RequestParam(required = false) LocalDate creationDate,
            @Parameter(hidden = true) @ParameterObject @PageableDefault(page = 0, size = 10,
                    sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable) {
        return plasticService.getall(typeName, diameter, manufacturerId, colorId, creationDate, pageable);
    }

    @PostMapping
    public Plastic create(
            @Parameter(name = "typeName", description = "Имя типа пластика, например 'ABS, PET-G...'", example = "ABS")
            @RequestParam() String typeName,
            @Parameter(name = "diameter", description = "Диаметр пластика, например '1.75, 3.0...'", example = "1.75")
            @RequestParam() Float diameter,
            @Parameter(name = "manufacturerId", description = "ID производителя пластика, например '1', '2',...'", example = "0")
            @RequestParam() Long manufacturerId,
            @Parameter(name = "colorId", description = "ID цвета пластика, например '1', '2',...'", example = "0")
            @RequestParam() Long colorId) {
        return plasticService.save(typeName, diameter, manufacturerId, colorId);
    }

    @DeleteMapping("id")
    public void delete(@Parameter(name = "id", description = "Id пластика", example = "1")
                       @RequestParam("id") Long id) {
        plasticService.delete(id);
    }

    @PatchMapping("id")
    public Plastic update(
            @Parameter(name = "id", description = "Id пластика", example = "1")
            @RequestParam("id") Long id,
            @Parameter(name = "typeName", description = "Имя типа пластика, например 'ABS, PET-G...'", example = "ABS")
            @RequestParam(required = false) String typeName,
            @Parameter(name = "diameter", description = "Диаметр пластика, например '1.75, 3.0...'", example = "1.75")
            @RequestParam(required = false) Float diameter,
            @Parameter(name = "manufacturerId", description = "ID производителя пластика, например '1', '2',...'", example = "0")
            @RequestParam(required = false) Long manufacturerId,
            @Parameter(name = "colorId", description = "ID цвета пластика, например '1', '2',...'", example = "0")
            @RequestParam(required = false) Long colorId) {
        return plasticService.update(id, typeName, diameter, manufacturerId, colorId);
    }

    @PutMapping("id")
    public Plastic replace(
            @Parameter(name = "id", description = "Id пластика", example = "1")
            @RequestParam("id") Long id,
            @Parameter(name = "typeName", description = "Имя типа пластика, например 'ABS, PET-G...'", example = "ABS")
            @RequestParam() String typeName,
            @Parameter(name = "diameter", description = "Диаметр пластика, например '1.75, 3.0...'", example = "1.75")
            @RequestParam() Float diameter,
            @Parameter(name = "manufacturerId", description = "ID производителя пластика, например '1', '2',...'", example = "0")
            @RequestParam(required = false) Long manufacturerId,
            @Parameter(name = "colorId", description = "ID цвета пластика, например '1', '2',...'", example = "0")
            @RequestParam(required = false) Long colorId) {
        return plasticService.update(id, typeName, diameter, manufacturerId, colorId);
        }

        @GetMapping("getById")
        public Plastic getById (@Parameter(name = "id", description = "Id пластика", example = "1")
        @RequestParam("id") Long id){
            return plasticService.getById(id);
        }
    }
