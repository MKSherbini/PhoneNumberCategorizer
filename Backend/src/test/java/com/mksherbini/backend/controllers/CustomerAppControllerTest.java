package com.mksherbini.backend.controllers;

import com.mksherbini.backend.models.dto.CustomerDto;
import com.mksherbini.backend.models.orm.Customer;
import com.mksherbini.backend.services.CustomerFilterService;
import org.junit.jupiter.api.BeforeEach;
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
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    private String url;

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

    @Test
    void testUrlExposed() {
        final var customers = List.of(
                new CustomerDto("Knuckles", "(256) 714660221", "Uganda", 256, true),
                new CustomerDto("Fake Knuckles", "(256) 3142345678", "Uganda", 256, false),
                new CustomerDto("Not Knuckles", "(258) 847651504", "Mozambique", 258, true),
                new CustomerDto("Not Knuckles 2", "(212) 691933626", "Morocco", 212, true)
        );
        when(customerFilterService.getCustomersByFilter(any(), any(), anyInt(), anyInt()))
                .thenReturn(customers);

        url = "http://localhost:" + port + servletContext.getContextPath();
        ResponseEntity<List<Customer>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Customer>>() {
                });
        System.out.println("response = " + response);
        assertThat(response.getBody())
                .isNotNull()
                .isNotEmpty();
    }
}
