package nl.tudelft.goalkeeper.parser.results.files.mas2g;

import lombok.Getter;
import nl.tudelft.goalkeeper.parser.results.files.File;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch.LaunchPolicy;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch.LaunchRule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class Mas2gFile extends File{

    @Getter private File environment = null;

    @Getter List<LaunchRule> launchRules;

    @Getter LaunchPolicy launchPolicy;

    /**
     * Creates a new File instance.
     *
     * @param fileName fileName of the file.
     * @throws IOException Thrown when file could not be read.
     */
    protected Mas2gFile(String fileName) throws IOException {
        super(fileName);
        launchRules = new ArrayList<>();
    }
}
