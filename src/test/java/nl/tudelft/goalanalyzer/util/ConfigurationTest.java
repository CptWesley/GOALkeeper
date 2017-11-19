package nl.tudelft.goalanalyzer.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Configuration class.
 */
class ConfigurationTest {

    private Configuration config;

    /**
     * Sets up configuration instance before each test.
     */
    @BeforeEach
    void setup() {
        config = Configuration.getInstance();
    }

    /**
     * Clear configuration after each test.
     */
    @AfterEach
    void cleanup() {
        Configuration.clear();
    }

    /**
     * Checks that the singleton pattern is implemented correctly.
     */
    @Test
    void sameInstanceTest() {
        assertThat(config).isSameAs(Configuration.getInstance());
    }

    /**
     * Checks that the singleton pattern is implemented correctly.
     */
    @Test
    void differentInstanceTest() {
        Configuration.clear();
        assertThat(config).isNotSameAs(Configuration.getInstance());
    }
}
