package com.mksherbini.backend.controllers;

import com.mksherbini.backend.models.dto.CustomerDto;
import com.mksherbini.backend.services.CustomerFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerAppController {
    private final CustomerFilterService customerFilterService;

    @GetMapping
    public List<CustomerDto> findAllCustomers(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Boolean state,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return customerFilterService.getCustomersByFilter(country, state, page, size);
    }
}
