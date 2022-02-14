package com.mksherbini.backend.adapters;

import com.mksherbini.backend.models.dto.CustomerDto;
import com.mksherbini.backend.models.orm.Customer;
import com.mksherbini.backend.services.PhoneNumberValidationService;
import com.mksherbini.backend.utils.CountryPhoneClassifier;
import com.mksherbini.backend.utils.PhoneCountryCodeExtractor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerAdapter implements GenericAdapter<Customer, CustomerDto> {
    private final PhoneNumberValidationService phoneNumberValidationService;
    private final CountryPhoneClassifier countryPhoneClassifier;

    @Override
    public CustomerDto adaptOrmToDto(Customer customer) {
        final CustomerDto customerDto = new ModelMapper().map(customer, CustomerDto.class);
        customerDto.setPhoneState(phoneNumberValidationService.isValid(customer.getPhone()));
        customerDto.setCountryCode(PhoneCountryCodeExtractor.getCountryCode(customer.getPhone()));
        customerDto.setCountryName(countryPhoneClassifier.getCountryNameByCode(customerDto.getCountryCode()));
        return customerDto;
    }
}
