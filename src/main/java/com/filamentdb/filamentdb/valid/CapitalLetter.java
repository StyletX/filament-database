package com.filamentdb.filamentdb.valid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, FIELD})
@Constraint(validatedBy = CapitalLetterValidator.class)
public @interface CapitalLetter {

    String message() default "Должно быть с большой буквы!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}