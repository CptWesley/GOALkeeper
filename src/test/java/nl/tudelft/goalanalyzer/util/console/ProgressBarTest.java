package nl.tudelft.goalanalyzer.util.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Cedric Willekens (4530373) on 12/7/2017.
 */
public class ProgressBarTest {
    private ProgressBar progressBar;

    @BeforeEach
    void init() {
        this.progressBar = ProgressBar.getINSTANCE(3);
    }

    @Test
    void update() {
        progressBar.update();
        progressBar.update();
    }
}
