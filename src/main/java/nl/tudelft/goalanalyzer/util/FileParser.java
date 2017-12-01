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
        List<File> fileList = new ArrayList<>();
        for (String file: files) {
            File temp = new File(file);
            if (temp.isFile() &&
                    FilenameUtils.getExtension(file).toLowerCase().equals("mas2g")) {
                fileList.add(temp);
            }
        }
        return fileList;
    }

}
