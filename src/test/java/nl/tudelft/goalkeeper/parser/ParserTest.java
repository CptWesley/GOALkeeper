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
        assertThat(result.getViolations()).filteredOn(o -> o.isError()).hasSize(3);
        assertThat(result.getViolations()).filteredOn(o -> !o.isError()).hasSize(1);
    }

    /**
     * Add checks for correct files.
     */
    @Test
    void successTest() {
        ParseResult result = parser.parse("src/test/resources/testfiles/bw4t-working/bw4t.mas2g");
        assertThat(result.getViolations()).hasSize(1);
        assertThat(result.isSuccessful()).isTrue();
        assertThat(result.getViolations()).filteredOn(o -> o.isError()).hasSize(0);
        assertThat(result.getViolations()).filteredOn(o -> !o.isError()).hasSize(1);
    }
}
