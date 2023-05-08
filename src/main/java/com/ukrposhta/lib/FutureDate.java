package com.ukrposhta.lib;

import com.ukrposhta.lib.validator.FutureDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = FutureDateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureDate {
    String message() default "This date can't be a past date.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
