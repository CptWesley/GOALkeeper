package nl.tudelft.goalanalyzer.util.console;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Console class.
 */
class ConsoleTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static final String NEWLINE = System.getProperty("line.separator");

    /**
     * Prepare test environment before each test.
     */
    @BeforeEach
    void setup() {
        Console.setUseColor(false);
        System.setOut(new PrintStream(out));
    }

    /**
     * Clear test environment after each test.
     */
    @AfterEach
    void breakdown() {
        System.setOut(null);
    }

    /**
     * Checks that the use color setter works.
     */
    @Test
    void setUseColorTest() {
        assertThat(Console.usesColor()).isFalse();
        Console.setUseColor(true);
        assertThat(Console.usesColor()).isTrue();
    }

    /**
     * Checks that printing is done correctly.
     */
    @Test
    void printTest() {
        Console.print("abcd");
        assertThat(out.toString()).isEqualTo("abcd");
    }

    /**
     * Checks that printing is done correctly.
     */
    @Test
    void printColorDisabledTest() {
        Console.print("xyz", ConsoleColor.GREEN);
        assertThat(out.toString()).isEqualTo("xyz");
    }

    /**
     * Checks that printing is done correctly.
     */
    @Test
    void printColorEnabledTest() {
        Console.setUseColor(true);
        Console.print("hjk", ConsoleColor.PURPLE);
        assertThat(out.toString()).isEqualTo("\u001B[35mhjk");
    }

    /**
     * Checks that printing is done correctly.
     */
    @Test
    void printlnTest() {
        Console.println("efgh");
        assertThat(out.toString()).isEqualTo("efgh" + NEWLINE);
    }

    /**
     * Checks that printing is done correctly.
     */
    @Test
    void printlnColorDisabledTest() {
        Console.println("poil", ConsoleColor.BLACK);
        assertThat(out.toString()).isEqualTo("poil" + NEWLINE);
    }

    /**
     * Checks that printing is done correctly.
     */
    @Test
    void printlnColorEnabledTest() {
        Console.setUseColor(true);
        Console.println("das", ConsoleColor.CYAN);
        assertThat(out.toString()).isEqualTo("\u001B[36mdas" + NEWLINE);
    }
}
