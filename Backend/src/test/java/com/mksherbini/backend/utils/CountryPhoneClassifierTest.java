package com.mksherbini.backend.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CountryPhoneClassifierTest {
    @Autowired
    private CountryPhoneClassifier countryPhoneClassifier;

    private static final String COUNTRY_NAME = "Uganda";
    private static final Integer COUNTRY_CODE = 256;
    private static final String PHONE_REGEX = "\\(256\\)\\ ?\\d{9}$";

    @Test
    void getCodeByCountryName() {
        assertThat(countryPhoneClassifier.getCodeByCountryName(COUNTRY_NAME))
                .isNotNull()
                .isEqualTo(COUNTRY_CODE);
    }

    @Test
    void getCountryNameByCode() {
        assertThat(countryPhoneClassifier.getCountryNameByCode(COUNTRY_CODE))
                .isNotNull()
                .isNotEmpty()
                .contains(COUNTRY_NAME);
    }

    @Test
    void getPhoneRegexByCode() {
        assertThat(countryPhoneClassifier.getPhoneRegexByCode(COUNTRY_CODE))
                .isNotNull()
                .isNotEmpty()
                .contains(PHONE_REGEX);
    }
}
