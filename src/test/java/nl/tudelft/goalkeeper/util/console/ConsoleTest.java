package nl.tudelft.goalkeeper.util.console;

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
@SuppressWarnings("PMD.TooManyMethods")
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
        assertThat(out.toString(UTF8)).isEqualTo("\u001B[35mhjk\u001B[0m");
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
        assertThat(out.toString(UTF8)).isEqualTo("\u001B[36mdas\u001B[0m" + NEWLINE);
    }

    /**
     * Checks that printing an object works.
     */
    @Test
    void printObjectTest() throws UnsupportedEncodingException {
        Console.print(1);
        assertThat(out.toString(UTF8)).isEqualTo("1");
    }

    /**
     * Checks that printing an object works with colors.
     */
    @Test
    void printColorObjectTest() throws UnsupportedEncodingException {
        Console.setUseColor(true);
        Console.print(-1, ConsoleColor.RED);
        assertThat(out.toString(UTF8)).isEqualTo(
                ConsoleColor.RED.getAnsi() + "-1\u001B[0m");
    }

    /**
     * Checks that printing an object and appending a newline works.
     */
    @Test
    void printlnObjectTest() throws UnsupportedEncodingException {
        Console.println(0.5f);
        assertThat(out.toString(UTF8)).isEqualTo("0.5" + NEWLINE);
    }

    /**
     * Checks that printing an object and appending a newline works with colors.
     */
    @Test
    void printlnColorObjectTest() throws UnsupportedEncodingException {
        Console.setUseColor(true);
        Console.println(-0.5, ConsoleColor.WHITE);
        assertThat(out.toString(UTF8)).isEqualTo(
                ConsoleColor.WHITE.getAnsi() + "-0.5\u001B[0m" + NEWLINE);
    }
}
