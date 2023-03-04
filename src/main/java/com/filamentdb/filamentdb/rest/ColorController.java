package com.filamentdb.filamentdb.rest;

import com.filamentdb.filamentdb.model.Color;
import com.filamentdb.filamentdb.service.ColorService;
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
@RequestMapping("colors")
@Tag(name = "Контроллер для цветов пластика",
        description = "Контролер для работы с объектами класса Color")
public class ColorController {
    private final ColorService colorService;

    @GetMapping
    @PageableAsQueryParam
    public Page<Color> getAll(
            @Parameter(name = "colorName", description = "Название цвета пластика", example = "Чёрный")
            @RequestParam(required = false) String colorName,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(name = "creationDate", description = "Дата создания записи в базе", example = "2023-03-08")
            @RequestParam(required = false) LocalDate creationDate,
            @Parameter(hidden = true) @ParameterObject @PageableDefault(page = 0, size = 10, sort = "creationDate",
                    direction = Sort.Direction.ASC) Pageable pageable) {
        return colorService.getAll(colorName, creationDate, pageable);
    }

    @PostMapping
    public Color create(
            @Parameter(name = "colorName", description = "Название цвета пластика", example = "Чёрный")
            @RequestParam() String colorName) {
        return colorService.save(colorName);
    }

    @DeleteMapping("id")
    public void delete(@Parameter(name = "id", description = "Id цвета пластика", example = "1")
                       @RequestParam("id") Long id) {
        colorService.delete(id);
    }

    @PatchMapping("id")
    public Color update(
            @Parameter(name = "id", description = "Id цвета пластика", example = "1")
            @RequestParam("id") Long id,
            @Parameter(name="colorName", description ="Название цвета пластика", example ="Чёрный")
            @RequestParam() String colorName) {
    return colorService.update(id, colorName);
    }

    @PutMapping("id")
    public Color replace(
            @Parameter(name = "id", description = "Id цвета пластика", example = "1")
            @RequestParam("id") Long id,
            @Parameter(name="colorName", description ="Название цвета пластика", example ="Чёрный")
            @RequestParam() String colorName) {
        return colorService.update(id, colorName);
    }

    @GetMapping("getById")
    public Color getById(@Parameter(name = "id", description = "Id цвета пластика", example = "1")
                             @RequestParam("id") Long id){
        return colorService.getById(id);
    }

}
