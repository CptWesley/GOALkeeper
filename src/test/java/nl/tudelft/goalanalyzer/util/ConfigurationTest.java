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

    /**
     * Checks that a parameter is added correctly.
     */
    @Test
    void addParameterTest() {
        assertThat(config.hasParameter("paname")).isFalse();
        config.addParameter("paname", "true");
        assertThat(config.hasParameter("paname")).isTrue();
        assertThat(config.getParameter("paname").getAsBoolean()).isTrue();
    }

    /**
     * Checks that files are handled properly.
     */
    @Test
    void filesSizeTest() {
        assertThat(config.getFiles()).isEmpty();
        config.addFile("awesome-file.txt");
        assertThat(config.getFiles()).hasSize(1);
        config.addFile("awesome-file.txt");
        assertThat(config.getFiles()).hasSize(1);
    }

    /**
     * Checks that files are handled properly.
     */
    @Test
    void filesAddTest() {
        config.addFile("awesome-file2.txt");
        assertThat(config.getFiles()).contains("awesome-file2.txt");
    }

    /**
     * Checks if we can parse arguments correctly.
     */
    @Test
    void loadTest() {
        String[] args = new String[] {
                "-flag",
                "-var=value",
                "src/main/resources/rules.json",
                "src/main/resources/",
                "fghdghfd"
        };
        Configuration.load(args);
        assertThat(config.hasParameter("flag"));
        assertThat(config.getParameter("flag").getAsBoolean()).isTrue();
        assertThat(config.getParameter("var").getAsString()).isEqualTo("value");
        assertThat(config.getFiles()).hasSize(1);
    }
}
