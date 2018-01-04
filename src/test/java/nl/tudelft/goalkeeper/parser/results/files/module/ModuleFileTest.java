package nl.tudelft.goalkeeper.parser.results.files.module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ModuleFile class.
 */
class ModuleFileTest extends ModuleTest {

    private ModuleFile file;

    /**
     * {@inheritDoc}
     */
    @Override
    Module getModule() {
        return file;
    }

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() throws IOException {
        file = new ModuleFile("src/test/resources/testfiles/emptyfile.txt");
    }
}
