package com.filamentdb.filamentdb.internal.CustomAnnotations.PlasticAnnotations;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.*;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Positive
@Retention(RUNTIME)
@NotNull(message = "Поле не должно быть пустым!")
@Digits(integer = 3, fraction = 2, message = "Диаметр пластика должен быть вещественным числом, например 1.75")
@Parameter(name = "diameter", description = "Диаметр пластика в мм, например '1.75, 3.0...'", example = "1.75")
public @interface PlasticDiameter {
}
