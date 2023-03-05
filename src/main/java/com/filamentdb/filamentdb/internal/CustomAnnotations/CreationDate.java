package com.filamentdb.filamentdb.internal.CustomAnnotations;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Positive
@Retention(RUNTIME)
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
@Parameter(name = "creationDate", description = "Дата создания записи в базе", example = "2023-03-08")
public @interface CreationDate {
}
