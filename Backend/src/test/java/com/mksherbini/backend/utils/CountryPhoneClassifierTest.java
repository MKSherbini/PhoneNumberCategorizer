package com.mksherbini.backend.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CountryPhoneClassifierTest {
    @Autowired
    private CountryClassifier countryClassifier;

    private static final String COUNTRY_NAME = "Uganda";
    private static final int COUNTRY_CODE = 256;
    private static final String PHONE_REGEX = "\\(256\\)\\ ?\\d{9}$";

    @Test
    void getCodeByCountryName() {
        assertThat(countryClassifier.getCodeByCountryName(COUNTRY_NAME))
                .isNotEmpty()
                .contains(COUNTRY_CODE);
    }

    @Test
    void getCountryNameByCode() {
        assertThat(countryClassifier.getCountryNameByCode(COUNTRY_CODE))
                .isNotEmpty()
                .contains(COUNTRY_NAME);
    }

    @Test
    void getPhoneRegexByCode() {
        assertThat(countryClassifier.getPhoneRegexByCode(COUNTRY_CODE))
                .isNotEmpty()
                .contains(PHONE_REGEX);
    }
}
