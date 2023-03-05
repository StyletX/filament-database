package com.filamentdb.filamentdb.valid;


import com.filamentdb.filamentdb.internal.CustomAnnotations.ManufacturerAnnotations.ManufacturerInn;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;

public class ManufacturerInnValidator implements ConstraintValidator<ManufacturerInn, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value != null) {
            long temp = value;
            ArrayList<Integer> array = new ArrayList<>();
            do {
                array.add(0, (int) (temp % 10));
                temp /= 10;
            } while (temp > 0);
            int sum = array.get(0) * 2 + array.get(1) * 4 + array.get(2) * 10 +
                    array.get(3) * 3 + array.get(4) * 5 + array.get(5) * 9 +
                    array.get(6) * 4 + array.get(7) * 6 + array.get(8) * 8;
            int key = sum % 11;
            if (key > 9) {
                key = key % 10;
            }
            return key == array.get(9);
        }
        return false;
    }

}