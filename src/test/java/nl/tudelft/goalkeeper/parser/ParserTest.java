package nl.tudelft.goalkeeper.parser;

import krTools.parser.SourceInfo;
import languageTools.errors.Message;
import nl.tudelft.goalkeeper.checking.violations.Violation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Parser class.
 */
class ParserTest {

    private Parser parser;

    /**
     * Sets up test environment before each test.
     */
    @BeforeEach
    void setup() {
        parser = new Parser();
    }

    /**
     * Checks that we can correctly parse failing mas files.
     */
    @Test
    void failureTest() {
        ParseResult result = parser.parse("src/test/resources/testfiles/failed.mas2g");
        assertThat(result.getViolations()).hasSize(4);
        assertThat(result.isSuccessful()).isFalse();
    }

    /**
     * Checks that we can properly convert errors to violations.
     */
    @Test
    void toViolationTest() {
        Message error = Mockito.mock(Message.class);
        SourceInfo si = Mockito.mock(SourceInfo.class);
        Mockito.when(error.getSource()).thenReturn(si);
        Mockito.when(error.toShortString()).thenReturn("bla");
        Mockito.when(si.getLineNumber()).thenReturn(23);
        Mockito.when(si.getSource()).thenReturn("foobar");
        Violation violation = Parser.toViolation(error);
        assertThat(violation.getEndingLine()).isEqualTo(23);
        assertThat(violation.getStartingLine()).isEqualTo(23);
        assertThat(violation.getFile()).isEqualTo("foobar");
        assertThat(violation.getMessage()).isEqualTo("bla");
    }
}
