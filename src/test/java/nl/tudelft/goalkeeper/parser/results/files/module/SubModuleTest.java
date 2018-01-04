package nl.tudelft.goalkeeper.parser.results.files.module;

import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for the SubModule class.
 */
class SubModuleTest extends ModuleTest {

    private SubModule module;

    /**
     * {@inheritDoc}
     */
    @Override
    Module getModule() {
        return module;
    }

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        module = new SubModule();
    }
}
