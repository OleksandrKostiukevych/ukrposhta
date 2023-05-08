package com.ukrposhta.lib.validator;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PastDateValidatorTest {
    private final PastDateValidator validator = new PastDateValidator();

    @Test
    public void isValid_pastDate_ok() {
        assertTrue(validator.isValid(LocalDate.now().minusDays(1), null));
    }

    @Test
    public void isValid_futureDate_ok() {
        assertFalse(validator.isValid(LocalDate.now().plusDays(1), null));
    }

    @Test
    public void isValid_todayDate_ok() {
        assertFalse(validator.isValid(LocalDate.now(), null));
    }

    @Test
    public void isValid_nullInput_ok() {
        assertFalse(validator.isValid(null, null));
    }
}
