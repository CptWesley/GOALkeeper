package nl.tudelft.goalanalyzer.rules;

import com.sun.media.sound.InvalidFormatException;
import nl.tudelft.goalanalyzer.exceptions.NotParsedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test class for the RuleSetParser class
 */
public class RuleSetParserTest {

    private static String content;

    private RuleSetParser parser;

    /**
     * Reads json file at start of testing.
     */
    @BeforeAll
    static void setupAll() {
        content = new Scanner(RuleSetParserTest.class.getClassLoader()
                        .getResourceAsStream("rules.json"), "UTF-8")
                        .useDelimiter("\\A").next();
    }

    /**
     * Sets up testing environment before each test.
     * @throws InvalidFormatException should never be thrown.
     */
    @BeforeEach
    void setup() throws InvalidFormatException {
        parser = new RuleSetParser();
        parser.parse(content);
    }

    /**
     * Checks that exception is not thrown when format is valid.
     */
    @Test
    void parseTest() {
        parser = new RuleSetParser();
        assertThatCode(() -> parser.parse("{}")).doesNotThrowAnyException();
        assertThat(parser.isParsed()).isTrue();
    }

    /**
     * Checks that correct exception is thrown when parsing fails.
     */
    @Test
    void parseExceptionTest() {
        parser = new RuleSetParser();
        assertThatThrownBy(() -> parser.parse(""))
                .isInstanceOf(InvalidFormatException.class)
                .hasMessage("Invalid JSON file.");
        assertThat(parser.isParsed()).isFalse();
    }

    /**
     * Checks that error severity getter is functioning correctly.
     */
    @Test
    void getErrorSeverityTest() throws InvalidFormatException {
        assertThat(parser.getErrorSeverity()).isEqualTo(2);
    }

    /**
     * Checks that a NotParsedException is thrown when we execute the method
     * before parsing.
     */
    @Test
    void getErrorSeverityNotParsedTest() {
        parser = Mockito.spy(new RuleSetParser());
        assertThatThrownBy(() -> parser.getErrorSeverity())
                .isInstanceOf(NotParsedException.class);
        Mockito.when(parser.isParsed()).thenReturn(true);
        assertThatThrownBy(() -> parser.getErrorSeverity())
                .isInstanceOf(NotParsedException.class);
    }

    /**
     * Checks that an InvalidFormatException is thrown when we execute the method
     * while the setting is not defined.
     */
    @Test
    void getErrorSeverityNotDefinedTest() throws InvalidFormatException {
        parser = new RuleSetParser();
        parser.parse("{}");
        assertThatThrownBy(() -> parser.getErrorSeverity())
                .isInstanceOf(InvalidFormatException.class)
                .hasMessage("Missing 'error-severity' setting.");
    }

    /**
     * Checks that an InvalidFormatException is thrown when we execute the method
     * while the setting is wrongly defined.
     */
    @Test
    void getErrorSeverityWronglyDefinedTest() throws InvalidFormatException {
        parser = new RuleSetParser();
        parser.parse("{\"error-severity\": 3.2}");
        assertThatThrownBy(() -> parser.getErrorSeverity())
                .isInstanceOf(InvalidFormatException.class)
                .hasMessage("Invalid 'error-severity' type.");
    }
}
