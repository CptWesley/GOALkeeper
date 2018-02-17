package nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class LaunchPolicy {
    private List<LaunchRule> launchRules;

    public LaunchPolicy() {
        this.launchRules = new ArrayList<>();
    }

    public List<LaunchRule> getLaunchRules() {
        return Collections.unmodifiableList(launchRules);
    }
}
