package nl.tudelft.goalanalyzer.rules;

import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;
import nl.tudelft.goalanalyzer.exceptions.NotParsedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test class for the RuleSetParser class
 */
class RuleSetParserTest {

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
     * @throws MalformedRulesException should never be thrown.
     */
    @BeforeEach
    void setup() throws MalformedRulesException {
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
                .isInstanceOf(MalformedRulesException.class)
                .hasMessage("Invalid JSON file.");
        assertThat(parser.isParsed()).isFalse();
    }

    /**
     * Checks that error severity getter is functioning correctly.
     */
    @Test
    void getErrorSeverityTest() throws MalformedRulesException {
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
    void getErrorSeverityNotDefinedTest() throws MalformedRulesException {
        parser = new RuleSetParser();
        parser.parse("{}");
        assertThatThrownBy(() -> parser.getErrorSeverity())
                .isInstanceOf(MalformedRulesException.class)
                .hasMessage("Missing 'error-severity' setting.");
    }

    /**
     * Checks that an InvalidFormatException is thrown when we execute the method
     * while the setting is wrongly defined.
     */
    @Test
    void getErrorSeverityWronglyDefinedTest() throws MalformedRulesException {
        parser = new RuleSetParser();
        parser.parse("{\"error-severity\": 3.2}");
        assertThatThrownBy(() -> parser.getErrorSeverity())
                .isInstanceOf(MalformedRulesException.class)
                .hasMessage("Invalid 'error-severity' type.");
    }

    /**
     * Checks that rules are fetched properly. Smokey test.
     * @throws MalformedRulesException
     */
    @Test
    void getRulesTest() throws MalformedRulesException {
        Map<String, Rule> rules = parser.getRules();
        assertThat(rules.containsKey("LOC")).isTrue();
        Rule loc = rules.get("LOC");
        assertThat(loc.isEnabled()).isTrue();
        assertThat(loc.getStages().size()).isEqualTo(3);
        Stage s0 = loc.getStages().get(0);
        Stage s1 = loc.getStages().get(1);
        Stage s2 = loc.getStages().get(2);
        assertThat(s0.getSeverity()).isEqualTo(0);
        assertThat(s1.getSeverity()).isEqualTo(1);
        assertThat(s0.getMin()).isEqualTo(Double.MIN_VALUE);
        assertThat(s0.getMax()).isEqualTo(200);
        assertThat(s1.getMin()).isEqualTo(200);
        assertThat(s1.getMax()).isEqualTo(300);
        assertThat(s2.getMin()).isEqualTo(300);
        assertThat(s2.getMax()).isEqualTo(Double.MAX_VALUE);
    }
}
