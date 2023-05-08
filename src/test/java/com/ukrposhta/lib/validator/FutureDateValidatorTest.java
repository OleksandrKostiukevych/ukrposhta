package com.ukrposhta.lib.validator;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FutureDateValidatorTest {
    private final FutureDateValidator validator = new FutureDateValidator();

    @Test
    void isValid_futureDate_ok() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertTrue(validator.isValid(futureDate, null));
    }

    @Test
    void isValid_todayDate_ok() {
        LocalDate todayDate = LocalDate.now();
        assertFalse(validator.isValid(todayDate, null));
    }

    @Test
    void isValid_pastDate_ok() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        assertFalse(validator.isValid(pastDate, null));
    }

    @Test
    void isValid_nullInput_ok() {
        assertFalse(validator.isValid(null, null));
    }
}
