package nl.tudelft.goalanalyzer.util;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class FileParser {

    /**
     * Get a list of all files which are in an array of strings and of type mas2g.
     * @param files An array of strings with the path to a file.
     * @return A list of Files which has been found which is a mas2g file.
     */
    public static List<File> getMasFiles(String[] files) {
        return getFileByType(files, "mas2g");
    }

    public static List<File> getModuleFiles(String[] files) {
        return getFileByType(files, "mod2g");
    }

    public static List<File> getActionFiles(String[] files) {
        return getFileByType(files, "act2g");
    }

    public static List<File> getTestFiles(String[] files) {
        return getFileByType(files, "test2g");
    }

    private static List<File> getFileByType(String[] files, String type) {
        List<File> fileList = new ArrayList<>();
        for (String file: files) {
            File temp = new File(file);
            if (temp.isFile()
                    && FilenameUtils.getExtension(file).toLowerCase().equals(type)) {
                fileList.add(temp);
            }
        }
        return fileList;
    }
}
