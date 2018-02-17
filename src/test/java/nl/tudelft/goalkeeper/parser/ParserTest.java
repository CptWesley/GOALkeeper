package nl.tudelft.goalkeeper.parser;

import nl.tudelft.goalkeeper.parser.results.ParseResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Parser class.
 */
class ParserTest {

    /**
     * Checks that we can correctly parse failing mas files.
     */
    @Test
    void failureTest() {
        ParseResult result = new Parser("src/test/resources/testfiles/failed.mas2g").parse();
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
        ParseResult result = new Parser("src/test/resources/testfiles/bw4t-working/bw4t.mas2g").parse();
        assertThat(result.getViolations()).hasSize(1);
        assertThat(result.isSuccessful()).isTrue();
        assertThat(result.getViolations()).filteredOn(o -> o.isError()).hasSize(0);
        assertThat(result.getViolations()).filteredOn(o -> !o.isError()).hasSize(1);
    }
}
