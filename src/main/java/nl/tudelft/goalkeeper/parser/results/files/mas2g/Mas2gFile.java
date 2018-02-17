package nl.tudelft.goalkeeper.parser.results.files.mas2g;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.parser.results.files.File;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch.LaunchPolicy;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch.LaunchRule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class Mas2gFile extends File{

    @Getter @Setter private java.io.File environment = null;

    private List<LaunchRule> launchRules;

    @Getter private LaunchPolicy launchPolicy;

    /**
     * Creates a new File instance.
     *
     * @param fileName fileName of the file.
     * @throws IOException Thrown when file could not be read.
     */
    public Mas2gFile(String fileName) throws IOException {
        super(fileName);
        launchRules = new ArrayList<>();
    }

    public void addLaunchRules(List<LaunchRule> rules) {
        launchRules.addAll(rules);
    }

    public List<LaunchRule> getLaunchRules() {
        return Collections.unmodifiableList(launchRules);
    }
}
