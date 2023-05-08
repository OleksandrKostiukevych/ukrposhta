package com.ukrposhta.lib;

import com.ukrposhta.lib.validator.FullNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = FullNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FullName {
    String message() default
            "Full name shouldn't contain any numbers or special characters and shouldn't be empty.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
