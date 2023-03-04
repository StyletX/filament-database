package com.filamentdb.filamentdb.service;

import com.filamentdb.filamentdb.model.Plastic;
import com.filamentdb.filamentdb.repository.PlasticRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Service
@Transactional
public class PlasticService extends CrudService<PlasticRepository> {
    private final PlasticRepository plasticRepository;

    public PlasticService(PlasticRepository repository, PlasticRepository plasticRepository) {
        super(repository);
        this.plasticRepository = plasticRepository;
    }

    public Page<Plastic> getall(String typeName, Float diameter, Long manufacturerId, Long colorId, LocalDate date, Pageable pageable) {
        if (Objects.nonNull(typeName)) {
            return plasticRepository.findAllByTypeNameIgnoreCase(typeName, pageable);
        }
        if (Objects.nonNull(diameter)) {
            return plasticRepository.findAllByDiameter(diameter, pageable);
        }
        if (Objects.nonNull(manufacturerId)) {
            return plasticRepository.findAllByManufacturerId(manufacturerId, pageable);
        }
        if (Objects.nonNull(colorId)) {
            return plasticRepository.findAllByColorId(colorId, pageable);
        }
        if (Objects.nonNull(date)) {
            return plasticRepository.findAllByCreationDateBetween(
                    date.atTime(LocalTime.MIN), date.atTime(LocalTime.MAX), pageable);
        }
        return plasticRepository.findAll(pageable);
    }

    private Plastic preSave(Plastic plastic, String typeName, Float diameter, Long manufacturerId, Long colorId) {
        plastic.setTypeName(typeName == null ? plastic.getTypeName() : typeName);
        plastic.setDiameter(diameter == null ? plastic.getDiameter() : diameter);
        plastic.setManufacturerId(manufacturerId == null ? plastic.getManufacturerId() : manufacturerId);
        plastic.setColorId(colorId == null ? plastic.getColorId() : colorId);
        return plastic;
    }

    public Plastic save(String typeName, Float diameter, Long manufacturerId, Long colorId) {
        Plastic plastic = new Plastic();
        return plasticRepository.save(preSave(plastic, typeName, diameter, manufacturerId, colorId));
    }

    public Plastic getById(Long id) {
        return plasticRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Plastic update(Long id, String typeName, Float diameter, Long manufacturerId, Long colorId) {
        Plastic plastic = getById(id);
        return plasticRepository.save(preSave(plastic, typeName, diameter, manufacturerId, colorId));
    }
}
