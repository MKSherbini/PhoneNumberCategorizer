package com.mksherbini.backend.adapters;

import com.mksherbini.backend.models.dto.CustomerDto;
import com.mksherbini.backend.models.orm.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerAdapterTest {

    @Autowired
    CustomerAdapter customerAdapter;

    @Test
    void adaptOrmToDto() {
        final Customer customer = new Customer(0, "Knuckles", "(256) 714660221");
        final CustomerDto customerDto = new CustomerDto("Knuckles", "(256) 714660221"
                , "Uganda", 256, true);

        final CustomerDto outputDto = customerAdapter.adaptOrmToDto(customer);
        Assertions.assertThat(outputDto)
                .isNotNull()
                .isEqualTo(customerDto);
    }
}
