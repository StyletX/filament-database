package com.filamentdb.filamentdb.internal.CustomAnnotations.ManufacturerAnnotations;

import com.filamentdb.filamentdb.valid.CapitalLetter;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Parameter(name = "city", description = "Город", example = "Владимир")
@CapitalLetter(message = "Название города должно начинаться с большой буквы")
@NotBlank(message = "Поле не должно быть пустым или состоять только из пробелов!")
public @interface ManufacturerCity {
}
