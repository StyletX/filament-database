package com.filamentdb.filamentdb.rest;

import com.filamentdb.filamentdb.model.Plastic;
import com.filamentdb.filamentdb.service.PlasticService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
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
public class PlasticController {
    private final PlasticService plasticService;

    @GetMapping  // localhost:8080/manfs?page=0&size=10&sort=id,desc  //asc
//    @JsonView(JsonViews.ManufacturerView.class)
    public Page<Plastic> getAll(@RequestParam(required = false) String typeName,
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                @RequestParam(required = false) LocalDate date,
                                @ParameterObject @PageableDefault(page = 0, size = 10, sort = "typeName",
                                        direction = Sort.Direction.ASC)
                                     Pageable pageable) {
        return plasticService.getall(typeName, date, pageable);
    }

    @PostMapping
    public Plastic create(@Valid @RequestBody Plastic plastic) {
        return plasticService.save(plastic);
    }

    @DeleteMapping("{id}")
    public void delete(@RequestParam("id") Long id) {
        plasticService.delete(id);
    }

    @PatchMapping("{id}")
    public Plastic update(@RequestParam("id") Long id, @RequestBody Plastic patch) {
        return plasticService.update(id, patch);
    }

    //    @GetMapping("{id}")
    @GetMapping("getById")
//    public Manufacturer getById(@PathVariable("id") Long id) {
    public Plastic getById(@RequestParam("id") Long id) {
        return plasticService.getById(id);
    }
}
