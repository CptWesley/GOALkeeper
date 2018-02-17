package nl.tudelft.goalkeeper.parser.results.files.mas2g;

import nl.tudelft.goalkeeper.parser.results.files.File;

import java.io.IOException;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class Mas2gFile extends File{
    /**
     * Creates a new File instance.
     *
     * @param fileName fileName of the file.
     * @throws IOException Thrown when file could not be read.
     */
    protected Mas2gFile(String fileName) throws IOException {
        super(fileName);
    }
}
