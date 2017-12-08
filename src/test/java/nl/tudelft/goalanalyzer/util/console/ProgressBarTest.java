package nl.tudelft.goalanalyzer.util.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Cedric Willekens (4530373) on 12/7/2017.
 */
public class ProgressBarTest {
    private ProgressBar progressBar;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void init() {
        this.progressBar = ProgressBar.getINSTANCE(3);
        this.outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void update() {
        progressBar.update();
        assertThat(this.outputStream.toString()).isEqualTo("\r 33% ################ /");
        progressBar.update();
        assertThat(this.outputStream.toString()).isEqualTo("\r 33% ################ /" +
                "\r 66% ################################# -");
        progressBar.update();
        assertThat(this.outputStream.toString()).isEqualTo("\r 33% ################ /" +
                "\r 66% ################################# -" +
        "\r100% ################################################## \\");
        progressBar.update();
        assertThat(this.outputStream.toString()).isEqualTo("\r 33% ################ /" +
                "\r 66% ################################# -" +
                "\r100% ################################################## \\" +
                "\r 33% ################ /");
    }
}
