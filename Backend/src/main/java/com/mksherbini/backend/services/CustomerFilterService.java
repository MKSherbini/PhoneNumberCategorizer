package com.mksherbini.backend.services;

import com.mksherbini.backend.adapters.CustomerAdapter;
import com.mksherbini.backend.models.dto.CustomerDto;
import com.mksherbini.backend.models.orm.Customer;
import com.mksherbini.backend.repos.CustomerJpaRepo;
import com.mksherbini.backend.utils.CountryPhoneClassifier;
import com.mksherbini.backend.utils.PhoneCountryCodeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomerFilterService {
    private final CustomerJpaRepo customerJpaRepo;
    private final PhoneNumberValidationService phoneNumberValidationService;
    private final CountryPhoneClassifier countryPhoneClassifier;
    private final CustomerAdapter customerAdapter;

    public List<CustomerDto> getCustomersByFilter(String country, Boolean phoneState) {
        var customerStream = customerJpaRepo.findAll().stream();
        customerStream = filterStreamByCountry(customerStream, country);
        customerStream = filterStreamByPhoneState(customerStream, phoneState);

        return customerAdapter.adaptOrmToDto(
                customerStream.collect(Collectors.toList())
        );
    }

    public List<CustomerDto> paginateCustomers(List<CustomerDto> customers, int pageNumber, int pageSize) {
        var customerStream = customers.stream();

        customerStream = paginateStream(customerStream, pageNumber, pageSize);

        return customerStream.collect(Collectors.toList());
    }

    private <T> Stream<T> paginateStream(Stream<T> stream, int pageNumber, int pageSize) {
        return stream.skip((long) pageSize * pageNumber).limit(pageSize);
    }

    private Stream<Customer> filterStreamByCountry(Stream<Customer> stream, String country) {
        if (country == null)
            return stream;
        return stream.filter(c -> country.equalsIgnoreCase(
                countryPhoneClassifier.getCountryNameByCode(
                        PhoneCountryCodeExtractor.getCountryCode(
                                c.getPhone()
                        ))));
    }

    private Stream<Customer> filterStreamByPhoneState(Stream<Customer> stream, Boolean phoneState) {
        if (phoneState == null)
            return stream;
        return stream.filter(c -> phoneNumberValidationService.isValid(c.getPhone()) == phoneState);
    }


}
