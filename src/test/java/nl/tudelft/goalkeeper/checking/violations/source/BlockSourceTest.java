package nl.tudelft.goalkeeper.checking.violations.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the BlockSource class.
 */
class BlockSourceTest extends SourceTest {

    private static int STARTINGLINE = 0;
    private static int ENDINGLINE = 103;
    private BlockSource source;

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
        source = new BlockSource(FILENAME, STARTINGLINE, ENDINGLINE);
    }

    /**
     * Checks that we retrieve the correct starting line number.
     */
    @Test
    void getStartingLineTest() {
        assertThat(source.getStartingLine()).isEqualTo(STARTINGLINE);
    }

    /**
     * Checks that we retrieve the correct ending line number.
     */
    @Test
    void getEndingLineTest() {
        assertThat(source.getEndingLine()).isEqualTo(ENDINGLINE);
    }

    /**
     * Checks that we convert it to the correct string.
     */
    @Test
    void toStringTest() {
        assertThat(source.toString()).isEqualTo("in '" + FILENAME
                + "' at lines " + STARTINGLINE + "-" + ENDINGLINE);
    }
}
