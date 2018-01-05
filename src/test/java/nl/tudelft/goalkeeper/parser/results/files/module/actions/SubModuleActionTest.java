package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import nl.tudelft.goalkeeper.parser.results.files.module.SubModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the SubModuleAction class.
 */
class SubModuleActionTest {

    private SubModuleAction action;
    private SubModule module;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        module = Mockito.mock(SubModule.class);
        action = new SubModuleAction(module);
    }

    /**
     * Checks if we return the identifier correctly.
     */
    @Test
    void getIdentifierTest() {
        assertThat(action.getIdentifier()).isEqualTo("submodule/0");
    }

    /**
     * Checks that we return the module correctly.
     */
    @Test
    void getModuleTest() {
        assertThat(action.getModule()).isSameAs(module);
    }

    /**
     * Checks that we create the stringed version correctly.
     */
    @Test
    void toStringTest() {
        Mockito.when(module.toString()).thenReturn("SALT");
        assertThat(action.toString()).startsWith("{").endsWith("}").contains("SALT");
    }

    /**
     * Checks that an object is equal to itself.
     */
    @Test
    void equalsSelf() {
        assertThat(action).isEqualTo(action);
        assertThat(action.hashCode()).isEqualTo(action.hashCode());
    }

    /**
     * Checks that an object is equal to the same object.
     */
    @Test
    void equalsSame() {
        SubModuleAction other = new SubModuleAction(module);
        assertThat(action).isEqualTo(other);
        assertThat(action.hashCode()).isEqualTo(other.hashCode());
    }

    /**
     * Checks that an object is not equal to null.
     */
    @Test
    void notEqualsNull() {
        assertThat(action).isNotEqualTo(null);
    }

    /**
     * Checks that an object is not equal to an object of a different type.
     */
    @Test
    void notEqualsDifferentType() {
        assertThat(action).isNotEqualTo("");
    }

    /**
     * Checks that an object is not equal to an object with different module.
     */
    @Test
    void notEqualsDifferentModule() {
        assertThat(action).isNotEqualTo(new SubModuleAction(Mockito.mock(SubModule.class)));
    }
}
