package com.mksherbini.backend.utils;

import com.mksherbini.backend.exceptions.PhoneNumberParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberClassifier {
    static final String COUNTRY_CODE_RGX = "^\\((\\d{3})\\)";

    private PhoneNumberClassifier() {
    }

    public static int getCountryCode(String phone) {
        final Pattern pattern = Pattern.compile(COUNTRY_CODE_RGX);
        final Matcher matcher = pattern.matcher(phone);

        if (!matcher.find() || matcher.groupCount() != 1) {
            throw new PhoneNumberParseException("Country code not found");
        }

        return Integer.parseInt(matcher.group(1));
    }

}
