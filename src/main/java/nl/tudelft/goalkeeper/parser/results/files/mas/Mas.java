package nl.tudelft.goalkeeper.parser.results.files.mas;

import nl.tudelft.goalkeeper.parser.results.files.File;

import java.io.IOException;

/**
 * Class representing a MAS.
 */
public final class Mas extends File {
    /**
     * Creates a new File instance.
     * @param fileName fileName of the file.
     * @throws IOException Thrown when file could not be read.
     */
    protected Mas(String fileName) throws IOException {
        super(fileName);
    }
}
