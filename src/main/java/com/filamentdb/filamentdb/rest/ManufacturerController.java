package com.filamentdb.filamentdb.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.filamentdb.filamentdb.model.Manufacturer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.filamentdb.filamentdb.service.ManufacturerService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

//    @Autowired
//    public void setManufacturerService(ManufacturerService manufacturerService) {
//        this.manufacturerService = manufacturerService;
//    }

//    @GetMapping("hello")  // localhost:8080/manf/hello
    //public String hello() {return "Hello!";}
//    public String get() {
//        return manufacturerService.hello();
//    }

    @GetMapping  // localhost:8080/manfs?page=0&size=10&sort=id,desc  //asc
//    @JsonView(JsonViews.ManufacturerView.class)
    public Page<Manufacturer> getAll(@RequestParam(required = false) String name,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                     @RequestParam(required = false) LocalDate date,
                                     @PageableDefault(page = 0, size = 10, sort = "name",
                                             direction = Sort.Direction.ASC) Pageable pageable) {
        return manufacturerService.getall(name, date, pageable);
    }

    @PostMapping
    public Manufacturer create(@RequestBody Manufacturer manufacturer) {
        return manufacturerService.save(manufacturer);
    }

    @DeleteMapping("{id}")
    public void delete(@RequestParam("id") Long id) {
        manufacturerService.delete(id);
    }

    @PatchMapping("{id}")
    public Manufacturer update(@RequestParam("id") Long id, @RequestBody Manufacturer patch) {
        return manufacturerService.update(id, patch);
    }

//    @GetMapping("{id}")
    @GetMapping("getById")
//    public Manufacturer getById(@PathVariable("id") Long id) {
    public Manufacturer getById(@RequestParam("id") Long id) {
        return manufacturerService.getById(id);
    }
}