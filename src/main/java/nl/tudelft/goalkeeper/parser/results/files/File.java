package nl.tudelft.goalkeeper.parser.results.files;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Abstract class for file types.
 */
public abstract class File {

    @Getter private String[] content; //NOPMD PMD can't handle Lombok.
    @Getter private String source; //NOPMD PMD can't handle Lombok.

    /**
     * Creates a new File instance.
     * @param fileName fileName of the file.
     * @throws IOException Thrown when file could not be read.
     */
    protected File(String fileName) throws IOException {
        this.source = fileName;
        content = Files.lines(Paths.get(fileName)).toArray(String[]::new);
    }
}
