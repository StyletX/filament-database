package com.filamentdb.filamentdb.internal.CustomAnnotations;

import com.filamentdb.filamentdb.valid.CapitalLetter;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@NotBlank(message = "Поле не должно быть пустым или состоять только из пробелов!")
@CapitalLetter(message = "Название цвета пластика должно начинаться с большой буквы")
@Parameter(name = "typeName", description = "Название цвета пластика, например 'Чёрный, Красный...'", example = "Чёрный")
public @interface ColorName {
}
