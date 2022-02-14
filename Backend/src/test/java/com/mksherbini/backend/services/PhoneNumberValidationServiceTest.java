package com.mksherbini.backend.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
class PhoneNumberValidationServiceTest {

    @Autowired
    private PhoneNumberValidationService phoneNumberValidationService;

    private static final String VALID_PHONE_NUMBER = "(256) 714660221";
    private static final String INVALID_PHONE_NUMBER = "(256) 3142345678";

    @Test
    void whenValidPhoneNumber() {
        assertTrue(phoneNumberValidationService.isValid(VALID_PHONE_NUMBER));
    }

    @Test
    void whenInvalidPhoneNumber() {
        assertFalse(phoneNumberValidationService.isValid(INVALID_PHONE_NUMBER));
    }
}
