package com.filamentdb.filamentdb.service;

import com.filamentdb.filamentdb.model.Color;
import com.filamentdb.filamentdb.model.Manufacturer;
import com.filamentdb.filamentdb.model.Plastic;
import com.filamentdb.filamentdb.repository.ColorRepository;
import com.filamentdb.filamentdb.repository.ManufacturerRepository;
import com.filamentdb.filamentdb.repository.PlasticRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.xml.ws.Response;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class PlasticService {
    private final PlasticRepository plasticRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ColorRepository colorRepository;

   /* public PlasticService(PlasticRepository plasticRepository, ManufacturerRepository manufacturerRepository,
                          ColorRepository colorRepository) {
//        super(repository);    //------------------------???????????????
        this.plasticRepository = plasticRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.colorRepository = colorRepository;
    }*/

  /*  private Response setAttributesForPlastic(PlasticDTO plasticDTO, Plastic plastic) {
        Color color = Utils.getEntity(colorRepository, plasticDTO.getPlasticID(), PLASTIC_);
        Manufacturer manufacturer = Utils.getEntity(manufacturerRepository, plasticDTO.getManufacturerID, MANUFACTURER_);

        plastic.setColor(color);
        plastic.setManufacturer(manufacturer);
        plastic.setTypeName(plasticDTO.getTypeName());

        return Response.of(plasticRepository.save(plastic));
    }

    public Response createPlastic(PlasticDTO plasticDTO) {
        return setAttributesForPlastic(plasticDTO, new Plastic());
    }*/

    public Page<Plastic> getall(String typeName, LocalDate date, Pageable pageable) {
        if (Objects.nonNull(typeName)) {
            return plasticRepository.findAllByTypeNameIgnoreCase(typeName, pageable);
        }
        if (Objects.nonNull(date)) {
            return plasticRepository.findAllByCreationDateBetween(
                    date.atTime(LocalTime.MIN), date.atTime(LocalTime.MAX), pageable);
        }
        return plasticRepository.findAll(pageable);
    }

    public Plastic save(Plastic plastic) {
        return plasticRepository.save(plastic);
    }

    public void delete(Long id) {
        Plastic byId = getById(id);
        plasticRepository.delete(byId);
    }

    public Plastic getById(Long id) {
        return plasticRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Plastic update(Long id, Plastic patch) {
        Plastic plastic = getById(id);
        plastic.setTypeName(patch.getTypeName());

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//        manufacturer.setUpdateDateTime(LocalDateTime.now());
        return plasticRepository.save(plastic);
    }

}
