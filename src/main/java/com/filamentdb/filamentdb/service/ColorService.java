package com.filamentdb.filamentdb.service;

import com.filamentdb.filamentdb.model.Color;
import com.filamentdb.filamentdb.repository.ColorRepository;
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
public class ColorService extends CrudService<ColorRepository> {
    private final ColorRepository colorRepository;

    public ColorService(ColorRepository repository, ColorRepository colorRepository) {
        super(repository);
        this.colorRepository = colorRepository;
    }

    public Page<Color> getAll(String colorName, LocalDate creationDate, Pageable pageable) {
        if (Objects.nonNull(colorName)) {
            return colorRepository.findAllByColorName(colorName, pageable);
        }
        if (Objects.nonNull(creationDate)) {
            return colorRepository.findAllByCreationDateBetween(
                    creationDate.atTime(LocalTime.MIN), creationDate.atTime(LocalTime.MAX), pageable);
        }
        return colorRepository.findAll(pageable);
    }

    public Color save(String colorName) {
        Color color = new Color();
        color.setColorName(colorName);
        return colorRepository.save(color);
    }

    public Color update(Long id, String colorName) {
        Color color = getById(id);
        color.setColorName(colorName == null ? color.getColorName() : colorName);
        return colorRepository.save(color);
    }

    public Color getById(Long id) {
        return colorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
