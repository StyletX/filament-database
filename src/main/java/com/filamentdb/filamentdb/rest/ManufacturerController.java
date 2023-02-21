package com.filamentdb.filamentdb.rest;

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
@RequestMapping("manfs")
@RequiredArgsConstructor
public class ManufacturerController {
    private ManufacturerService manufacturerService;

    @Autowired
    public void setManufacturerService(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("hello")  // localhost:8080/manf/hello
    //public String hello() {return "Hello!";}
    public String get() {
        return manufacturerService.hello();
    }

    @GetMapping  // localhost:8080/manfs?page=0&size=10&sort=id,desc  //asc
    public Page<Manufacturer> getAll(@RequestParam(required = false) String name,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                     @RequestParam(required = false) LocalDate date,
                                     @PageableDefault(page = 0, size = 10, sort = "name",
                                             direction = Sort.Direction.ASC) Pageable pageable) {
//        if (Objects.nonNull(name)) {
//            return repository.findAllByNameIgnoreCase(name, pageable);
//        }
//        if (Objects.nonNull(date)) {
//            return repository.findAllByCreateDateTimeBetween(
//                    date.atTime(LocalTime.MIN), date.atTime(LocalTime.MAX), pageable);
//        }
//        return repository.findAll(pageable);
        return manufacturerService.getall(name, date, pageable);
    }

    @PostMapping
    public Manufacturer create(@RequestBody Manufacturer manufacturer) {
        //return repository.save(manufacturer);
        return manufacturerService.save(manufacturer);
    }

    @DeleteMapping("{id}")
    public void delete(@RequestParam("id") Long id) {
//        Manufacturer byId = getById(id);
//        repository.delete(byId);
        manufacturerService.delete(id);
    }

    @PatchMapping("{id}")
    public Manufacturer update(@RequestParam("id") Long id, @RequestBody Manufacturer patch) {
//        Manufacturer manufacturer = getById(id);
//        manufacturer.setName(patch.getName());
//        manufacturer.setUpdateDateTime(LocalDateTime.now());
//        return repository.save(manufacturer);
        return manufacturerService.update(id, patch);
    }

    @GetMapping("{id}")
    //public Manufacturer getById(@PathVariable Long id) {
    public Manufacturer getById(@RequestParam("id") Long id) {
        //return repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return manufacturerService.getById(id);
    }
}