package com.filamentdb.filamentdb.service;

import com.filamentdb.filamentdb.model.Manufacturer;
import com.filamentdb.filamentdb.repository.ManufacturerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Service
@Transactional
public class ManufacturerService extends CrudService<ManufacturerRepository> {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository repository, ManufacturerRepository manufacturerRepository) {
        super(repository);
        this.manufacturerRepository = manufacturerRepository;
    }

    public Page<Manufacturer> getall(String name, String fullName, String city, String inn, LocalDate creationDate, Pageable pageable) {
        if (Objects.nonNull(name)) {
            return manufacturerRepository.findAllByNameIgnoreCase(name, pageable);
        }
        if (Objects.nonNull(fullName)) {
            return manufacturerRepository.findAllByFullNameIgnoreCase(fullName, pageable);
        }
        if (Objects.nonNull(city)) {
            return manufacturerRepository.findAllByCityIgnoreCase(city, pageable);
        }
        if (Objects.nonNull(inn)) {
            return manufacturerRepository.findAllByInn(inn, pageable);
        }
        if (Objects.nonNull(creationDate)) {
            return manufacturerRepository.findAllByCreationDateBetween(
                    creationDate.atTime(LocalTime.MIN), creationDate.atTime(LocalTime.MAX), pageable);
        }

        return manufacturerRepository.findAll(pageable);
    }

    @SneakyThrows
    private Manufacturer preSave(Manufacturer manufacturer, String name, String fullName, String city, String inn) { // plastic????
//        Manufacturer bufManufacturer = new Manufacturer();
        manufacturer.setName(name == null ? manufacturer.getName() : name);
        manufacturer.setCity(city == null ? manufacturer.getCity() : city);
        manufacturer.setInn(inn == null ? manufacturer.getInn() : inn);
        manufacturer.setFullName(fullName == null ? manufacturer.getFullName() : fullName);
//        BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
//        notNull.copyProperties(manufacturer, bufManufacturer);
        return manufacturer;
    }

    public Manufacturer getById(Long id) {
        return manufacturerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Manufacturer update(Long id, String name, String fullName, String city, String inn) {  //plastic???
        Manufacturer manufacturer = getById(id);
        return manufacturerRepository.save(preSave(manufacturer, name, fullName, city, inn));
    }

    public Manufacturer save(String name, String fullName, String city, String inn) {
        Manufacturer manufacturer = new Manufacturer();
        return manufacturerRepository.save(preSave(manufacturer, name, fullName, city, inn));
    }


}
