package nl.tudelft.goalkeeper.checking.violations.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the CharacterSource class.
 */
class CharacterSourceTest extends SourceTest {

    private static int LINENUMBER = 12;
    private static int POSITION = 3;
    private CharacterSource source;

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
        source = new CharacterSource(FILENAME, LINENUMBER, POSITION);
    }

    /**
     * Checks that we retrieve the correct line number.
     */
    @Test
    void getLineTest() {
        assertThat(source.getLine()).isEqualTo(LINENUMBER);
    }

    /**
     * Checks that we retrieve the character position
     */
    @Test
    void getPositionTest() {
        assertThat(source.getPosition()).isEqualTo(POSITION);
    }

    /**
     * Checks that we convert it to the correct string.
     */
    @Test
    void toStringTest() {
        assertThat(source.toString()).isEqualTo("in '" + FILENAME
                + "' at line " + LINENUMBER
                + " at position " + POSITION);
    }
}
