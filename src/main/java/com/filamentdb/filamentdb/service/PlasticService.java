package com.filamentdb.filamentdb.service;

import com.filamentdb.filamentdb.model.Color;
import com.filamentdb.filamentdb.model.Manufacturer;
import com.filamentdb.filamentdb.model.Plastic;
import com.filamentdb.filamentdb.repository.ColorRepository;
import com.filamentdb.filamentdb.repository.ManufacturerRepository;
import com.filamentdb.filamentdb.repository.PlasticRepository;
import jakarta.xml.ws.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlasticService extends CrudService<PlasticRepository> {
    private final PlasticRepository plasticRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ColorRepository colorRepository;

    public PlasticService(PlasticRepository plasticRepository, ManufacturerRepository manufacturerRepository,
                          ColorRepository colorRepository) {
//        super(repository);    //------------------------???????????????
        this.plasticRepository = plasticRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.colorRepository = colorRepository;
    }

    private Response setAttributesForPlastic(PlasticDTO plasticDTO, Plastic plastic) {
        Color color = Utils.getEntity(colorRepository, plasticDTO.getPlasticID(), PLASTIC_);
        Manufacturer manufacturer = Utils.getEntity(manufacturerRepository, plasticDTO.getManufacturerID, MANUFACTURER_);

        plastic.setColor(color);
        plastic.setManufacturer(manufacturer);
        plastic.setTypeName(plasticDTO.getTypeName());

        return Response.of(plasticRepository.save(plastic));
    }

    public Response createPlastic(PlasticDTO plasticDTO) {
        return setAttributesForPlastic(plasticDTO, new Plastic());
    }


}
