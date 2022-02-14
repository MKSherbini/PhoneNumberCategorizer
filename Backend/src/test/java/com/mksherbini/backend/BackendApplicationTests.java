package com.mksherbini.backend;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
class BackendApplicationTests {

    @Test
    void contextLoads() {
        log.info("application starts successfully");
        assertTrue(true);
    }
}
