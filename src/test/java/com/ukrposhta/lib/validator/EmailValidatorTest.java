package com.ukrposhta.lib.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorTest {
    private final EmailValidator emailValidator = new EmailValidator();

    @Test
    void isValid_validEmail_ok() {
        assertTrue(emailValidator.isValid("mail@gmail.com", null));
    }

    @Test
    void isValid_emailWithoutDomainName_ok() {
        assertFalse(emailValidator.isValid("mail@gmail", null));
    }

    @Test
    void isValid_nullInput_ok() {
        assertFalse(emailValidator.isValid(null, null));
    }

    @Test
    void isValid_blankInput_ok() {
        assertFalse(emailValidator.isValid("", null));
    }
}
