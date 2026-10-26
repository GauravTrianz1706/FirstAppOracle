package com.sit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Integration test for SpringBootDataJpa application context.
 * Uses H2 in-memory database to avoid requiring a real database connection.
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@DisplayName("SpringBootDataJpa Application Context Tests")
class SpringDataJpaApplicationTests {

    @Test
    @DisplayName("Application context loads successfully")
    void contextLoads() {
        // Verifies that the Spring application context loads without errors
    }

    @Test
    @DisplayName("SpringBootDataJpa main method does not throw exception")
    void testMain_doesNotThrowException() {
        // Act & Assert
        assertDoesNotThrow(() -> SpringBootDataJpa.main(new String[]{}));
    }
}
