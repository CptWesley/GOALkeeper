package nl.tudelft.goalkeeper.parser.results.files;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the File class.
 */
abstract class FileTest {
    abstract File getFile();

    /**
     * Tests if we can set and get content correctly.
     */
    @Test
    void getContentTest() {
        String[] content = new String[] { "ABC", "bla", "last" };
        getFile().setContent(content);
        assertThat(getFile().getContent()).hasSize(3);
        assertThat(getFile().getContent()[0]).isEqualTo("ABC");
        assertThat(getFile().getContent()[1]).isEqualTo("bla");
        assertThat(getFile().getContent()[2]).isEqualTo("last");
    }
}
