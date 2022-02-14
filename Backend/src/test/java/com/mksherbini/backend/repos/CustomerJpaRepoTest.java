package com.mksherbini.backend.repos;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Slf4j
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
