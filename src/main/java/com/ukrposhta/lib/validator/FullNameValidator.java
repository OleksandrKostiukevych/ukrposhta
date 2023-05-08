package com.ukrposhta.lib.validator;

import com.ukrposhta.lib.FullName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FullNameValidator implements ConstraintValidator<FullName, String> {
    private static final String FULL_NAME_VALIDATOR_PATTERM = "^[a-zA-Z\\s]+";

    @Override
    public boolean isValid(String fullName, ConstraintValidatorContext constraintValidatorContext) {
        return fullName != null && fullName.matches(FULL_NAME_VALIDATOR_PATTERM);
    }
}
