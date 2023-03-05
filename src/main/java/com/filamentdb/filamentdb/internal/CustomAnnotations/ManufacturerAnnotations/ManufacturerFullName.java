package com.filamentdb.filamentdb.internal.CustomAnnotations.ManufacturerAnnotations;

import com.filamentdb.filamentdb.valid.CapitalLetter;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@NotBlank(message = "Поле не должно быть пустым или состоять только из пробелов!")
@CapitalLetter(message = "Полное наименование производителя должно начинаться с большой буквы")
@Parameter(name = "fullName", description = "Полное наименование производителя пластика", example = "High Tech Plast")
public @interface ManufacturerFullName {
}
