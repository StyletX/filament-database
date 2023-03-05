package com.filamentdb.filamentdb.internal.CustomAnnotations.PlasticAnnotations;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Positive
@Retention(RUNTIME)
@NotBlank(message = "Поле не должно быть пустым или состоять только из пробелов!")
@Parameter(name = "id", description = "ID пластика, например '1', '2',...", example = "1")
public @interface PlasticId {
}
