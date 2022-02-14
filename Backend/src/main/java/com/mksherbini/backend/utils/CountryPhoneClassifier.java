package com.mksherbini.backend.utils;

import com.mksherbini.backend.config.YamlPropertyLoaderFactory;
import com.mksherbini.backend.models.CountryPhone;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:country-codes.yaml", name = "CountryCodes", factory = YamlPropertyLoaderFactory.class)
@ConfigurationProperties(prefix = "country.phones")
public class CountryPhoneClassifier {
    @Setter
    private List<CountryPhone> countryPhones;

    public Integer getCodeByCountryName(String name) {
        return countryPhones.stream().filter(c -> c.getName().equals(name)).map(CountryPhone::getCode).findAny().orElse(null);
    }

    public String getCountryNameByCode(int code) {
        return countryPhones.stream().filter(c -> c.getCode() == code).map(CountryPhone::getName).findAny().orElse(null);
    }

    public String getPhoneRegexByCode(int code) {
        return countryPhones.stream().filter(c -> c.getCode() == code).map(CountryPhone::getPhoneRegex).findAny().orElse(null);
    }

}
