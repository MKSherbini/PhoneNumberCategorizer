package com.mksherbini.backend.utils;

import com.mksherbini.backend.exceptions.PhoneNumberParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneCountryCodeExtractorTest {

    @Test
    void getCountryCode() {
        final String phone = "(237) 697151594";
        assertEquals(237, PhoneCountryCodeExtractor.getCountryCode(phone));
    }

    @Test
    void shouldFailWhenLessThanThreeNumbers() {
        final String phone = "(27) 697151594";
        assertThrows(
                PhoneNumberParseException.class, () -> PhoneCountryCodeExtractor.getCountryCode(phone)
        );
    }

    @Test
    void shouldFailWhenMissingBrackets() {
        final String phone = "(237 697151594";
        assertThrows(
                PhoneNumberParseException.class, () -> PhoneCountryCodeExtractor.getCountryCode(phone)
        );
    }
}
