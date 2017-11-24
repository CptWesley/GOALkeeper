package nl.tudelft.goalanalyzer.util;

import lombok.Getter;
import nl.tudelft.goalanalyzer.exceptions.WrongFileTypeException;

/**
 * Class for indexing GOAL multi agent system projects.
 */
public final class MasIndexer {

    private static final String EXTENSION = ".mas2g";

    @Getter private String masFile;

    /**
     * Initializes a MasIndexer instance.
     * @param masFile Target mas file.
     */
    private MasIndexer(String masFile) {
        this.masFile = masFile;
    }

    /**
     * Creates a mas indexer.
     * @param masFile Mas file path.
     * @return Instance of a max indexer.
     */
    public static MasIndexer create(String masFile) throws WrongFileTypeException {
        int pathLength = masFile.length();
        if (pathLength < EXTENSION.length()
                || !masFile.substring(pathLength - EXTENSION.length(), pathLength)
                .toLowerCase().equals(EXTENSION)) {
            throw new WrongFileTypeException("Expected type: " + EXTENSION);
        }
        return new MasIndexer(masFile);
    }
}
