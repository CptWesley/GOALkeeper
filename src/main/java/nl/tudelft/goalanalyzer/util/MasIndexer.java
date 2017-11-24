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
     * @throws WrongFileTypeException Thrown when masFile is of wrong type.
     */
    public static MasIndexer create(String masFile) throws WrongFileTypeException {
        if (!getExtension(masFile).toLowerCase().equals(EXTENSION)) {
            throw new WrongFileTypeException("Expected type: " + EXTENSION);
        }
        return new MasIndexer(masFile);
    }

    /**
     * Gets the file extension of a file.
     * @param fileName File name to check for.
     * @return Extension of the file.
     */
    private static String getExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
    }
}
