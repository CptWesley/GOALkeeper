package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the ModuleAction class.
 */
class ModuleActionTest {

    private static final String SOURCE = "WERGWSDAG";

    private ModuleAction action;
    private Expression arg1, arg2;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        arg1 = Mockito.mock(Expression.class);
        arg2 = Mockito.mock(Expression.class);
        action = new ModuleAction(SOURCE, Arrays.asList(arg1, arg2));
    }

    /**
     * Check if identifier is returned correctly when target is not yet linked.
     */
    @Test
    void getIdentifierTest() {
        assertThat(action.getIdentifier()).isEqualTo("null/2");
    }

    /**
     * Checks that we return the target correctly.
     */
    @Test
    void getTargetTest() {
        assertThat(action.getTarget()).isEqualTo(SOURCE);
    }

    /**
     * Checks that we can set the module correctly.
     */
    @Test
    void setModuleTest() {
        ModuleFile module = Mockito.mock(ModuleFile.class);
        Mockito.when(module.getName()).thenReturn("ADS");
        action.setModule(module);
        assertThat(action.getModule()).isSameAs(module);
        assertThat(action.getIdentifier()).isEqualTo("ADS/2");
    }

    /**
     * Checks that the arguments are returned correctly.
     */
    @Test
    void getArgumentsTest() {
        assertThat(action.getArguments()).containsExactly(arg1, arg2);
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
        ModuleAction other = new ModuleAction(SOURCE, Arrays.asList(arg1, arg2));
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
     * Checks that an object is not equal to an object with a different target.
     */
    @Test
    void notEqualsDifferentTarget() {
        ModuleAction other = new ModuleAction("", Arrays.asList(arg1, arg2));
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks that an object is not equal to an object with different amount of arguments.
     */
    @Test
    void notEqualsDifferentArgumentsAmount() {
        ModuleAction other = new ModuleAction(SOURCE, Arrays.asList(arg1));
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks that an object is not equal to an object with different arguments.
     */
    @Test
    void notEqualsDifferentArguments() {
        ModuleAction other = new ModuleAction(SOURCE, Arrays.asList(arg2, arg1));
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks that an object is not equal to an object with different arguments.
     */
    @Test
    void notEqualsDifferentModule() {
        ModuleAction other = new ModuleAction(SOURCE, Arrays.asList(arg1, arg2));
        action.setModule(Mockito.mock(ModuleFile.class));
        other.setModule(Mockito.mock(ModuleFile.class));
        assertThat(action).isNotEqualTo(other);
    }

    /**
     * Checks if the toString method has the correct result.
     */
    @Test
    void toStringTest() {
        ModuleFile module = Mockito.mock(ModuleFile.class);
        Mockito.when(module.getName()).thenReturn("BAF");
        Mockito.when(arg1.toString()).thenReturn("a");
        Mockito.when(arg2.toString()).thenReturn("b");
        action.setModule(module);
        assertThat(action.toString()).isEqualTo("BAF/2(a, b)");
    }
}
