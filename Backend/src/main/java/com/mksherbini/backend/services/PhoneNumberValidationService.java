package com.mksherbini.backend.services;

import com.mksherbini.backend.utils.CountryPhoneClassifier;
import com.mksherbini.backend.utils.PhoneCountryCodeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PhoneNumberValidationService {
    private final CountryPhoneClassifier countryPhoneClassifier;

    public boolean isValid(String phone) {
        final var countryCode = PhoneCountryCodeExtractor.getCountryCode(phone);
        final var phoneRegex = countryPhoneClassifier.getPhoneRegexByCode(countryCode);

        final Pattern pattern = Pattern.compile(phoneRegex);
        final Matcher matcher = pattern.matcher(phone);

        return matcher.find();
    }
}
