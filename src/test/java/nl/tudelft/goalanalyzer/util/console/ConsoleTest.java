package nl.tudelft.goalanalyzer.util.console;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Console class.
 */
class ConsoleTest {
    private static final String UTF8 = "UTF-8";
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static final String NEWLINE = System.getProperty("line.separator");

    /**
     * Prepare test environment before each test.
     */
    @BeforeEach
    void setup() throws UnsupportedEncodingException {
        Console.setUseColor(false);
        System.setOut(new PrintStream(out, false, UTF8));
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
    void printTest() throws UnsupportedEncodingException {
        Console.print("abcd");
        assertThat(out.toString(UTF8)).isEqualTo("abcd");
    }

    /**
     * Checks that printing is done correctly.
     */
    @Test
    void printColorDisabledTest() throws UnsupportedEncodingException {
        Console.print("xyz", ConsoleColor.GREEN);
        assertThat(out.toString(UTF8)).isEqualTo("xyz");
    }

    /**
     * Checks that printing is done correctly.
     */
    @Test
    void printColorEnabledTest() throws UnsupportedEncodingException {
        Console.setUseColor(true);
        Console.print("hjk", ConsoleColor.PURPLE);
        assertThat(out.toString(UTF8)).isEqualTo("\u001B[35mhjk");
    }

    /**
     * Checks that printing is done correctly.
     */
    @Test
    void printlnTest() throws UnsupportedEncodingException {
        Console.println("efgh");
        assertThat(out.toString(UTF8)).isEqualTo("efgh" + NEWLINE);
    }

    /**
     * Checks that printing is done correctly.
     */
    @Test
    void printlnColorDisabledTest() throws UnsupportedEncodingException {
        Console.println("poil", ConsoleColor.BLACK);
        assertThat(out.toString(UTF8)).isEqualTo("poil" + NEWLINE);
    }

    /**
     * Checks that printing is done correctly.
     */
    @Test
    void printlnColorEnabledTest() throws UnsupportedEncodingException {
        Console.setUseColor(true);
        Console.println("das", ConsoleColor.CYAN);
        assertThat(out.toString(UTF8)).isEqualTo("\u001B[36mdas" + NEWLINE);
    }
}
