package nl.tudelft.goalkeeper.checking;

import nl.tudelft.goalkeeper.exceptions.MalformedRulesException;
import nl.tudelft.goalkeeper.rules.RuleSet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Test class for the CheckerRunner class.
 */
class CheckerRunnerTest {

    /**
     * Checks that there are no exceptions. Smoke test.
     * @throws URISyntaxException Should never be thrown.
     * @throws IOException Should never be thrown.
     * @throws MalformedRulesException Should never be thrown.
     */
    /*
    @Test
    void smokeTest() throws URISyntaxException, IOException, MalformedRulesException {
        //TODO: Replace by proper test.
        URL resource = this.getClass().getClassLoader().getResource("rules.json");
        String file = Paths.get(resource.toURI()).toFile().getAbsolutePath();
        RuleSet set = RuleSet.load(file);
        new CheckerRunner().run("", set);
    }
    */
}
