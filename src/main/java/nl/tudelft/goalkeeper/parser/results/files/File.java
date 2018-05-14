package nl.tudelft.goalkeeper.parser.results.files;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.FileSource;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.parts.Sourceable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Abstract class for file types.
 */
public abstract class File implements Sourceable {

    @Getter private String[] content; //NOPMD PMD can't handle Lombok.
    @Getter @Setter private Source source; //NOPMD PMD can't handle Lombok.

    /**
     * Creates a new File instance.
     * @param fileName fileName of the file.
     * @throws IOException Thrown when file could not be read.
     */
    protected File(String fileName) throws IOException {
        this.source = new FileSource(fileName);
        content = Files.lines(Paths.get(fileName)).toArray(String[]::new);
    }
}
