package nl.tudelft.goalanalyzer.util.console;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ConsoleColor enum.
 */
class ConsoleColorTest {

    /**
     * Checks if the correct ansi code is returned.
     */
    @Test
    void getAnsiTest() {
        assertThat(ConsoleColor.RED.getAnsi()).isEqualTo("\u001B[31m");
    }
}
