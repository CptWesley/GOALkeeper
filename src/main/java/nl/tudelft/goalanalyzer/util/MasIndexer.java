package nl.tudelft.goalanalyzer.util;

import lombok.Getter;
import nl.tudelft.goalanalyzer.exceptions.WrongFileTypeException;

import java.io.File;
import java.util.Arrays;

/**
 * Class for indexing GOAL multi agent system projects.
 */
public final class MasIndexer {

    private static final String EXTENSION = ".mas2g";
    private static final String[] TYPES = new String[] {
            ".mod2g",
            ".pl",
            ".act2g",
            ".test2g",
            ".mas2g"
    };

    @Getter private String masFile;

    /**
     * Initializes a MasIndexer instance.
     * @param masFile Target mas file.
     */
    private MasIndexer(String masFile) {
        this.masFile = masFile;
    }

    /**
     * Geys the file system of the indexer.
     * @return File system of the indexer.
     */
    public String[] getFileSystem() {
        File dir = new File(masFile).getParentFile();
        return Arrays.stream(dir.listFiles())
                .filter(file ->
                        isSupportedFile(file.getAbsolutePath()))
                .map(File::getAbsolutePath).toArray(String[]::new);
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
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * Checks if a file is supported.
     * @param fileName File name of the file.
     * @return True if file is supported.
     */
    private static boolean isSupportedFile(String fileName) {
        String extension = getExtension(fileName).toLowerCase();
        for (String type : TYPES) {
            if (extension.equals(type)) {
                return true;
            }
        }
        return false;
    }
}
