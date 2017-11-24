package nl.tudelft.goalanalyzer.util;

import nl.tudelft.goalanalyzer.exceptions.WrongFileTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test class for the MasIndexer class
 */
class MasIndexerTest {

    private MasIndexer indexer;

    /**
     * Sets up the testing environment before each test.
     * @throws WrongFileTypeException Should not be thrown.
     * @throws FileNotFoundException Should not be thrown.
     */
    @BeforeEach
    void setup() throws WrongFileTypeException, FileNotFoundException {
        indexer = MasIndexer.create("src/test/resources/testfiles/empty-project-files/mas.mas2g");
    }

    /**
     * Checks that too short file names are treated as invalid.
     */
    @Test
    void tooShortFileTypeTest() {
        assertThatThrownBy(() -> MasIndexer.create("b"))
                .isInstanceOf(WrongFileTypeException.class)
                .hasMessage("Expected type: .mas2g");
    }

    /**
     * Checks that invalid file types are treated as invalid.
     */
    @Test
    void wrongFileTypeTest() {
        assertThatThrownBy(() -> MasIndexer.create("blalalallalaal.txt"))
                .isInstanceOf(WrongFileTypeException.class)
                .hasMessage("Expected type: .mas2g");
    }

    /**
     * Checks that non-existing files throw an exception.
     */
    @Test
    void missingFileTest() {
        assertThatThrownBy(() -> MasIndexer.create("missingfile.mas2g"))
                .isInstanceOf(FileNotFoundException.class);
    }

    /**
     * Checks that file system is created correctly.
     */
    @Test
    void getFileSystemTest() {
        String[] files = indexer.getFileSystem();
        assertThat(files).hasSize(3)
                .contains(fullPath("mas.mas2g"))
                .contains(fullPath("module.mod2g"))
                .contains(fullPath("somefolder/actionspecs.act2g"));
    }

    /**
     * Gets the full path from a relative path.
     * @param path Relative Path.
     * @return Full path of the relative path.
     */
    private String fullPath(String path) {
        return new File("src/test/resources/testfiles/empty-project-files/"
                + path).getAbsolutePath();
    }
}
