package nl.tudelft.goalkeeper.checking.violations.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the LineSource class.
 */
class LineSourceTest extends SourceTest {

    private static int LINENUMBER = 24;
    private LineSource source;

    /**
     * {@inheritDoc}
     */
    @Override
    Source getSource() {
        return source;
    }

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        source = new LineSource(FILENAME, LINENUMBER);
    }

    /**
     * Checks that we retrieve the correct line number.
     */
    @Test
    void getLineTest() {
        assertThat(source.getLine()).isEqualTo(LINENUMBER);
    }

    /**
     * Checks that we convert it to the correct string.
     */
    @Test
    void toStringTest() {
        assertThat(source.toString()).isEqualTo("in '" + FILENAME + "' at line " + LINENUMBER);
    }
}
