package nl.tudelft.goalkeeper.parser.results.files.mas2g;

import nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch.LaunchRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Mas2gFileTest {
    private Mas2gFile mas2gFile;

    @BeforeEach
    void setup() throws IOException {
        mas2gFile = new Mas2gFile("src/test/resources/testfiles/emptyfile.txt");
    }

    @Test
    void checkLaunchRuleTest() {
        LaunchRule rule1 = Mockito.mock(LaunchRule.class);
        LaunchRule rule2 = Mockito.mock(LaunchRule.class);
        assertThat(mas2gFile.getLaunchRules()).isEmpty();
        mas2gFile.addLaunchRules(rule1);
        assertThat(mas2gFile.getLaunchRules()).hasSize(1);
        assertThat(mas2gFile.getLaunchRules()).containsExactly(rule1);
        mas2gFile.addLaunchRules(rule2);
        assertThat(mas2gFile.getLaunchRules()).hasSize(2);
        assertThat(mas2gFile.getLaunchRules()).containsExactly(rule1, rule2);
    }
}