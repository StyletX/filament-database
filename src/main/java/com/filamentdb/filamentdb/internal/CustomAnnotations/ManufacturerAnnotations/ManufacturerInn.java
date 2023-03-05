package com.filamentdb.filamentdb.internal.CustomAnnotations.ManufacturerAnnotations;

import com.filamentdb.filamentdb.valid.ManufacturerInnValidator;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.NonNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NonNull
@Positive
@Retention(RUNTIME)
@Constraint(validatedBy = ManufacturerInnValidator.class)
@Target({ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, FIELD})
@Max(value = 9_999_999_999L, message = "ИНН не может быть больше чем число '9_999_999_999'")
@Min(value = 1_000_000_000L, message = "ИНН не может быть меньше чем число '1_000_000_000'")
@Parameter(name = "inn", description = "ИНН производителя пластика", example = "1234567890")
@Digits(integer = 10, fraction = 0, message = "Поле ИНН должно содержать только 10 арабских цифр")
public @interface ManufacturerInn {
    String message() default "Введен некорректный ИНН!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
