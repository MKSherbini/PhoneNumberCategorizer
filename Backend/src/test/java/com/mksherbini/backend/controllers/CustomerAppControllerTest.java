package com.mksherbini.backend.controllers;

import com.mksherbini.backend.models.dto.CustomerDto;
import com.mksherbini.backend.services.CustomerFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerAppControllerTest {

    @MockBean
    private CustomerFilterService customerFilterService;

    @Autowired
    private CustomerAppController customerAppController;

    @Test
    void findAllCustomersByNoCountryAndNoPhoneCheckByPage() {
        final var customers = List.of(
                new CustomerDto("Knuckles", "(256) 714660221", "Uganda", 256, true),
                new CustomerDto("Fake Knuckles", "(256) 3142345678", "Uganda", 256, false),
                new CustomerDto("Not Knuckles", "(258) 847651504", "Mozambique", 258, true),
                new CustomerDto("Not Knuckles 2", "(212) 691933626", "Morocco", 212, true)
        );
        when(customerFilterService.getCustomersByFilter(null, null, 0, 10))
                .thenReturn(customers);

        final var filtered = customerAppController.findAllCustomers(null, null, 0, 10);

        assertThat(filtered)
                .isNotNull()
                .hasSize(4)
                .isEqualTo(customers);
    }
}
