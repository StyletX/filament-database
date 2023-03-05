package com.filamentdb.filamentdb.rest;

import com.filamentdb.filamentdb.internal.CustomAnnotations.ColorId;
import com.filamentdb.filamentdb.internal.CustomAnnotations.ColorName;
import com.filamentdb.filamentdb.internal.CustomAnnotations.CreationDate;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("colors")
@Tag(name = "Контроллер для цветов пластика", description = "Контролер для работы с объектами класса Color")
public class ColorController {
    private final ColorService colorService;

    @GetMapping
    @PageableAsQueryParam
    public Page<Color> getAll(@ColorName @RequestParam(required = false) String colorName,
                              @CreationDate @RequestParam(required = false) LocalDate creationDate,
                              @Parameter(hidden = true) @ParameterObject @PageableDefault(page = 0, size = 10,
                                      sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable) {
        return colorService.getAll(colorName, creationDate, pageable);
    }

    @PostMapping
    public Color create(@ColorName @RequestParam() String colorName) {
        return colorService.save(colorName);
    }

    @DeleteMapping("id")
    public void delete(@ColorId @RequestParam("colorId") Long id) {
        colorService.delete(id);
    }

    @PatchMapping("id")
    public Color update(@ColorId @RequestParam("colorId") Long id, @ColorName @RequestParam() String colorName) {
        return colorService.update(id, colorName);
    }

    @PutMapping("id")
    public Color replace(@ColorId @RequestParam("colorId") Long id, @ColorName @RequestParam() String colorName) {
        return colorService.update(id, colorName);
    }

    @GetMapping("getById")
    public Color getById(@ColorId @RequestParam("colorId") Long id) {
        return colorService.getById(id);
    }

}
