package com.mksherbini.backend.repos;

import com.mksherbini.backend.models.Customer;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Log
class CustomerJpaRepoTest {

    @Autowired
    private CustomerJpaRepo customerJpaRepo;

    @Test
    void testDatabaseFileLoaded() {
        assertThat(customerJpaRepo.findAll())
                .isNotNull()
                .isNotEmpty();
    }
}
