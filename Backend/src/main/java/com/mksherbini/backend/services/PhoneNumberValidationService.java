package com.mksherbini.backend.services;

import com.mksherbini.backend.utils.CountryClassifier;
import com.mksherbini.backend.utils.PhoneNumberClassifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneNumberValidationService {
    private final CountryClassifier countryClassifier;

    public boolean isValid(String phone) {
        final int countryCode = PhoneNumberClassifier.getCountryCode(phone);
        countryClassifier.getCountryNameByCode(countryCode);
        return false;
    }
}
