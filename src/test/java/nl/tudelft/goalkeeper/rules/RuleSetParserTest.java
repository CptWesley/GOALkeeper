package nl.tudelft.goalkeeper.rules;

import nl.tudelft.goalkeeper.exceptions.MalformedRulesException;
import nl.tudelft.goalkeeper.exceptions.NotParsedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test class for the RuleSetParser class
 */
class RuleSetParserTest {

    private static InputStream content;

    private RuleSetParser parser;

    /**
     * Reads json file at start of testing.
     */
    @BeforeAll
    static void setupAll() {
        content = RuleSetParserTest.class.getClassLoader()
                .getResourceAsStream("default_rules.xml");
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
        assertThatCode(() -> parser.parse(createStream("{}"))).doesNotThrowAnyException();
        assertThat(parser.isParsed()).isTrue();
    }

    /**
     * Checks that correct exception is thrown when parsing fails.
     */
    @Test
    void parseExceptionTest() {
        parser = new RuleSetParser();
        assertThatThrownBy(() -> parser.parse(createStream("")))
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
        parser.parse(createStream("{}"));
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
        parser.parse(createStream("{\"error-severity\": 3.2}"));
        assertThatThrownBy(() -> parser.getErrorSeverity())
                .isInstanceOf(MalformedRulesException.class)
                .hasMessage("Invalid 'error-severity' type.");
    }

    /**
     * Checks that error severity getter is functioning correctly.
     */
    @Test
    void getFailOnErrorTest() throws MalformedRulesException {
        assertThat(parser.getFailOnError()).isEqualTo(true);
    }

    /**
     * Checks that a MalformedRulesException is thrown when we execute the method
     * before parsing.
     */
    @Test
    void getFailOnErrorNotParsedTest() {
        parser = Mockito.spy(new RuleSetParser());
        assertThatThrownBy(() -> parser.getFailOnError())
                .isInstanceOf(NotParsedException.class);
        Mockito.when(parser.isParsed()).thenReturn(true);
        assertThatThrownBy(() -> parser.getFailOnError())
                .isInstanceOf(NotParsedException.class);
    }

    /**
     * Checks that an MalformedRulesException is thrown when we execute the method
     * while the setting is not defined.
     */
    @Test
    void getFailOnErrorNotDefinedTest() throws MalformedRulesException {
        parser = new RuleSetParser();
        parser.parse(createStream("{}"));
        assertThatThrownBy(() -> parser.getFailOnError())
                .isInstanceOf(MalformedRulesException.class)
                .hasMessage("Missing 'fail-on-error' setting.");
    }

    /**
     * Checks that rules are fetched properly. Smokey test.
     * @throws MalformedRulesException Should never be thrown.
     */
    @Test
    void getRulesTest() throws MalformedRulesException {
        Map<String, Rule> rules = parser.getRules();
        assertThat(rules.containsKey("LOC")).isTrue();
        assertThat(rules.containsKey("ELOC")).isTrue();
        Rule loc = rules.get("LOC");
        Rule eloc = rules.get("ELOC");
        assertThat(loc.isEnabled()).isTrue();
        assertThat(eloc.isEnabled()).isFalse();
        assertThat(loc.getStages().size()).isEqualTo(3);
        Stage s1 = loc.getStages().get(0);
        Stage s2 = loc.getStages().get(1);
        assertThat(s1.getSeverity()).isEqualTo(1);
        assertThat(s2.getSeverity()).isEqualTo(2);
        assertThat(s1.getLimit()).isEqualTo(200);
        assertThat(s2.getLimit()).isEqualTo(300);
    }

    private InputStream createStream(String input) {
        return new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    }
}
