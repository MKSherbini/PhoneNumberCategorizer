package com.mksherbini.backend.controllers;

import com.mksherbini.backend.models.Page;
import com.mksherbini.backend.models.RequestResponse;
import com.mksherbini.backend.models.dto.CustomerDto;
import com.mksherbini.backend.models.orm.Customer;
import com.mksherbini.backend.services.CustomerFilterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerAppControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private ServletContext servletContext;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CustomerFilterService customerFilterService;

    @Autowired
    private CustomerAppController customerAppController;

    @Test
    void findAllCustomersByNoCountryAndNoPhoneCheckByPageLowLimit() {
        final var customers = List.of(
                new CustomerDto("Knuckles", "(256) 714660221", "Uganda", 256, true),
                new CustomerDto("Fake Knuckles", "(256) 3142345678", "Uganda", 256, false),
                new CustomerDto("Not Knuckles", "(258) 847651504", "Mozambique", 258, true),
                new CustomerDto("Not Knuckles 2", "(212) 691933626", "Morocco", 212, true)
        );
        final var expectedPage = new Page(0, 4, 1, 4);
        when(customerFilterService.getCustomersByFilter(null, null))
                .thenReturn(customers);
        when(customerFilterService.paginateCustomers(customers, 0, 10))
                .thenReturn(customers);

        final var filtered = customerAppController.findAllCustomers(null, null, 0, 10);

        assertThat(filtered)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
        assertThat(filtered.getData())
                .isNotNull()
                .hasSize(4)
                .isEqualTo(customers);
        assertEquals(expectedPage, filtered.getPage());
    }

    @Test
    void findAllCustomersByNoCountryAndNoPhoneCheckByPageHighLimit() {
        final var customers = List.of(
                new CustomerDto("Knuckles", "(256) 714660221", "Uganda", 256, true),
                new CustomerDto("Fake Knuckles", "(256) 3142345678", "Uganda", 256, false),
                new CustomerDto("Not Knuckles", "(258) 847651504", "Mozambique", 258, true),
                new CustomerDto("Not Knuckles 2", "(212) 691933626", "Morocco", 212, true)
        );
        final var expectedCustomers = List.of(
                new CustomerDto("Not Knuckles", "(258) 847651504", "Mozambique", 258, true),
                new CustomerDto("Not Knuckles 2", "(212) 691933626", "Morocco", 212, true)
        );
        final var expectedPage = new Page(1, 2, 2, 4);
        when(customerFilterService.getCustomersByFilter(null, null))
                .thenReturn(customers);
        when(customerFilterService.paginateCustomers(customers, 1, 2))
                .thenReturn(expectedCustomers);

        final var filtered = customerAppController.findAllCustomers(null, null, 1, 2);

        assertThat(filtered)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
        assertThat(filtered.getData())
                .isNotNull()
                .hasSize(2)
                .isEqualTo(expectedCustomers);
        assertEquals(expectedPage, filtered.getPage());
    }

    @Test
    void testUrlExposed() {
        final var customers = List.of(
                new CustomerDto("Knuckles", "(256) 714660221", "Uganda", 256, true),
                new CustomerDto("Fake Knuckles", "(256) 3142345678", "Uganda", 256, false),
                new CustomerDto("Not Knuckles", "(258) 847651504", "Mozambique", 258, true),
                new CustomerDto("Not Knuckles 2", "(212) 691933626", "Morocco", 212, true)
        );
        when(customerFilterService.getCustomersByFilter(any(), any()))
                .thenReturn(customers);
        when(customerFilterService.paginateCustomers(customers, 0, 10))
                .thenReturn(customers);

        String url = "http://localhost:" + port + servletContext.getContextPath();
        ResponseEntity<RequestResponse<List<Customer>>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        assertThat(response.getBody())
                .isNotNull()
                .hasNoNullFieldsOrProperties();
        assertThat(response.getBody().getData())
                .isNotNull()
                .isNotEmpty();
        assertEquals(200, response.getStatusCodeValue());
    }
}
