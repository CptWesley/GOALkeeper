package nl.tudelft.goalkeeper.checking.violations.source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the FileSource class.
 */
class FileSourceTest extends SourceTest {

    private FileSource source;

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
        source = new FileSource(FILENAME);
    }

    /**
     * Checks that the toString method returns the correct string.
     */
    @Test
    void toStringTest() {
        assertThat(source.toString()).isEqualTo("in '" + FILENAME + "'");
    }

}
