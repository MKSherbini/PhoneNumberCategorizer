package com.mksherbini.backend.utils;

import com.mksherbini.backend.config.YamlPropertyLoaderFactory;
import com.mksherbini.backend.models.CountryPhone;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@PropertySource(value = "classpath:country-codes.yaml", name = "CountryCodes", factory = YamlPropertyLoaderFactory.class)
@ConfigurationProperties(prefix = "country.phones")
public class CountryClassifier {
    @Setter
    private List<CountryPhone> countryPhones;

    public Optional<Integer> getCodeByCountryName(String name) {
        return countryPhones.stream().filter(c -> c.getName().equals(name)).map(CountryPhone::getCode).findAny();
    }

    public Optional<String> getCountryNameByCode(int code) {
        return countryPhones.stream().filter(c -> c.getCode() == code).map(CountryPhone::getName).findAny();
    }

    public Optional<String> getPhoneRegexByCode(int code) {
        return countryPhones.stream().filter(c -> c.getCode() == code).map(CountryPhone::getPhoneRegex).findAny();
    }

}
