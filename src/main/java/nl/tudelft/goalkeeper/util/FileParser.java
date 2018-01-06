package nl.tudelft.goalkeeper.util;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A class which can parse files and split them on several types.
 */
public final class FileParser {

    private FileParser() { }

    /**
     * Get a list of all files which are in an array of strings and of type mas2g.
     * @param files An array of strings with the path to a file.
     * @return A list of Files which has been found which is a mas2g file.
     */
    public static List<File> getMasFiles(String[] files) {
        return getFileByType(files, "mas2g");
    }

    /**
     * Get a list of all files whicha re in an array of string and of type mod2g.
     * @param files An array of strings with the path to a file.
     * @return A list of ifles which has been found which is a mod2g file.
     */
    public static List<File> getModuleFiles(String[] files) {
        return getFileByType(files, "mod2g");
    }

    /**
     * Get a list of all files whicha re in an array of string and of type act2g.
     * @param files An array of strings with the path to a file.
     * @return A list of ifles which has been found which is a act2g file.
     */
    public static List<File> getActionFiles(String[] files) {
        return getFileByType(files, "act2g");
    }

    /**
     * Get a list of all files which are in an array of string and of type test2g.
     * @param files An array of strings with the path to a file.
     * @return A list of ifles which has been found which is a test2g file.
     */
    public static List<File> getTestFiles(String[] files) {
        return getFileByType(files, "test2g");
    }

    /**
     * Get a list of files which are of type @code{type}.
     * @param files The array of strings which are a path to a file.
     * @param type The extension the file should have.
     * @return A list of files which have as extension @code{type}.
     */
    private static List<File> getFileByType(String[] files, String type) {
        List<File> fileList = new ArrayList<>();
        for (String file: files) {
            File temp = new File(file);
            if (isOfType(file, type)) {
                fileList.add(temp);
            }
        }
        return fileList;
    }

    public static boolean isOfType(String file, String type) {
        File temp = new File(file);
        return temp.isFile()
                && FilenameUtils.getExtension(file).toLowerCase().equals(type);
    }
}
