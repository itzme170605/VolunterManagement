package com.ufund.api.ufundapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UfundApiApplicationTest {

    @Test
    void contextLoads() {
        // The contextLoads test will automatically check if the Spring application context starts up correctly.
        // If the context fails to load, this test will fail.
    }

    @Test
    void main() {
        // This test ensures that the Spring Boot application can run.
        UfundApiApplication.main(new String[] {});
        assert(true);
    }
}
