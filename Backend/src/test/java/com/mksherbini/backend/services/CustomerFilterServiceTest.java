package com.mksherbini.backend.services;

import com.mksherbini.backend.models.dto.CustomerDto;
import com.mksherbini.backend.models.orm.Customer;
import com.mksherbini.backend.repos.CustomerJpaRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerFilterServiceTest {

    @MockBean
    CustomerJpaRepo customerJpaRepo;

    @Autowired
    CustomerFilterService customerFilterService;


    @BeforeEach
    void setUp() {
        final var customers = List.of(
                new Customer(1, "Knuckles", "(256) 714660221"),
                new Customer(2, "Fake Knuckles", "(256) 3142345678"),
                new Customer(3, "Not Knuckles", "(258) 847651504"),
                new Customer(4, "Not Knuckles 2", "(212) 691933626")
        );
        when(customerJpaRepo.findAll())
                .thenReturn(customers);
    }

    @Test
    void filterCustomersByUgandaAndNoPhoneCheck() {
        final var customers = List.of(
                new CustomerDto("Knuckles", "(256) 714660221", "Uganda", 256, true),
                new CustomerDto("Fake Knuckles", "(256) 3142345678", "Uganda", 256, false)
        );

        final var filtered = customerFilterService.getCustomersByFilter("Uganda", null);

        assertThat(filtered)
                .isNotNull()
                .hasSize(2)
                .isEqualTo(customers);
    }

    @Test
    void filterCustomersByMoroccoAndNoPhoneCheck() {
        final var customers = List.of(
                new CustomerDto("Not Knuckles 2", "(212) 691933626", "Morocco", 212, true)

        );

        final var filtered = customerFilterService.getCustomersByFilter("Morocco", null);

        assertThat(filtered)
                .isNotNull()
                .hasSize(1)
                .isEqualTo(customers);
    }

    @Test
    void filterCustomersWrongCountryAndNoPhoneCheck() {
        final var filtered = customerFilterService.getCustomersByFilter("Egypt", null);

        assertThat(filtered)
                .isNotNull()
                .isEmpty();
    }

    @Test
    void filterCustomersByNoCountryAndNoPhoneCheck() {
        final var customers = List.of(
                new CustomerDto("Knuckles", "(256) 714660221", "Uganda", 256, true),
                new CustomerDto("Fake Knuckles", "(256) 3142345678", "Uganda", 256, false),
                new CustomerDto("Not Knuckles", "(258) 847651504", "Mozambique", 258, true),
                new CustomerDto("Not Knuckles 2", "(212) 691933626", "Morocco", 212, true)
        );

        final var filtered = customerFilterService.getCustomersByFilter(null, null);

        assertThat(filtered)
                .isNotNull()
                .hasSize(4)
                .isEqualTo(customers);
    }

    @Test
    void filterCustomersByNoCountryAndNoPhoneCheckByPage() {
        final var customers = List.of(
                new CustomerDto("Not Knuckles", "(258) 847651504", "Mozambique", 258, true),
                new CustomerDto("Not Knuckles 2", "(212) 691933626", "Morocco", 212, true)
        );

        final var filtered = customerFilterService.getCustomersByFilter(null, null, 1, 2);

        assertThat(filtered)
                .isNotNull()
                .hasSize(2)
                .isEqualTo(customers);
    }

    @Test
    void filterCustomersByNoCountryAndInvalidPhone() {
        final var customers = List.of(
                new CustomerDto("Fake Knuckles", "(256) 3142345678", "Uganda", 256, false)
        );

        final var filtered = customerFilterService.getCustomersByFilter(null, false);

        assertThat(filtered)
                .isNotNull()
                .hasSize(1)
                .isEqualTo(customers);
    }

    @Test
    void filterCustomersByNoCountryAndValidPhone() {
        final var customers = List.of(
                new CustomerDto("Knuckles", "(256) 714660221", "Uganda", 256, true),
                new CustomerDto("Not Knuckles", "(258) 847651504", "Mozambique", 258, true),
                new CustomerDto("Not Knuckles 2", "(212) 691933626", "Morocco", 212, true)
        );

        final var filtered = customerFilterService.getCustomersByFilter(null, true);

        assertThat(filtered)
                .isNotNull()
                .hasSize(3)
                .isEqualTo(customers);
    }

    @Test
    void filterCustomersByUgandaAndInvalidPhone() {
        final var customers = List.of(
                new CustomerDto("Fake Knuckles", "(256) 3142345678", "Uganda", 256, false)
        );

        final var filtered = customerFilterService.getCustomersByFilter("Uganda", false);

        assertThat(filtered)
                .isNotNull()
                .hasSize(1)
                .isEqualTo(customers);
    }

    @Test
    void filterCustomersByUgandaAndValidPhone() {
        final var customers = List.of(
                new CustomerDto("Knuckles", "(256) 714660221", "Uganda", 256, true)
        );

        final var filtered = customerFilterService.getCustomersByFilter("Uganda", true);

        assertThat(filtered)
                .isNotNull()
                .hasSize(1)
                .isEqualTo(customers);
    }


}
