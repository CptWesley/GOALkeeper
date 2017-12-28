package nl.tudelft.goalkeeper.checking.violations.source;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Abstract test class for the Source implementations.
 */
abstract class SourceTest {
    static final String FILENAME = "bla123";
    abstract Source getSource();

    /**
     * Checks that the file name is retrieved correctly.
     */
    @Test
    void getFileTest() {
        assertThat(getSource().getFile()).isEqualTo(FILENAME);
    }
}
