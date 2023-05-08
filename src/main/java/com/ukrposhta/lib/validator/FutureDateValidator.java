package com.ukrposhta.lib.validator;

import com.ukrposhta.lib.FutureDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate deadline,
                           ConstraintValidatorContext constraintValidatorContext) {
        return deadline != null && deadline.isAfter(LocalDate.now());
    }
}
