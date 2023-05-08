package com.ukrposhta.lib.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FullNameValidatorTest {

    private final FullNameValidator validator = new FullNameValidator();

    @Test
    void isValid_validInput_ok() {
        assertTrue(validator.isValid("Petrenko Petro Petrovych", null));
    }

    @Test
    void isValid_wordStartsWithLowercaseLetter_ok() {
        assertTrue(validator.isValid("petrenko Petro Petrovych", null));
        assertTrue(validator.isValid("Petrenko petro Petrovych", null));
        assertTrue(validator.isValid("Petrenko Petro petrovych", null));
    }

    @Test
    void isValid_nullInput_ok() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void isValid_blankInput_ok() {
        assertFalse(validator.isValid("", null));
    }

    @Test
    void isValid_twoWords_ok() {
        assertTrue(validator.isValid("Lee Choo", null));
    }

    @Test
    void isValid_oneWord_ok() {
        assertTrue(validator.isValid("Petrenko", null));
    }

    @Test
    void isValid_wordContainsOneCharacter_ok() {
        assertTrue(validator.isValid("Petrenko P Petrovych", null));
    }
}
