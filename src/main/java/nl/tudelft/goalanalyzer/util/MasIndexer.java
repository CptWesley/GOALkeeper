package nl.tudelft.goalanalyzer.util;

import lombok.Getter;
import nl.tudelft.goalanalyzer.exceptions.WrongFileTypeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
        return getFileSystem(dir).toArray(new String[0]);
    }

    /**
     * Creates a mas indexer.
     * @param masFile Mas file path.
     * @return Instance of a max indexer.
     * @throws WrongFileTypeException Thrown when masFile is of wrong type.
     */
    public static MasIndexer create(String masFile) throws WrongFileTypeException, FileNotFoundException {
        if (!getExtension(masFile).toLowerCase().equals(EXTENSION)) {
            throw new WrongFileTypeException("Expected type: " + EXTENSION);
        }
        if (!new File(masFile).exists()) {
            throw new FileNotFoundException();
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

    /**
     * Gets all files in all sub directories.
     * @param directory Directory to start in.
     * @return Collection of all file names in sub directories.
     */
    private static List<String> getFileSystem(File directory) {
        List<String> files = new ArrayList<>();
        for (File file : directory.listFiles()) {
            if (file.isFile() && isSupportedFile(file.getName())) {
                files.add(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                files.addAll(getFileSystem(file));
            }
        }
        return files;
    }
}
