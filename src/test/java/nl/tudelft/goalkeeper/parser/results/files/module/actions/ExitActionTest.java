package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *  Test class for the ExitAction class.
 */
class ExitActionTest {
    private static final String IDENTIFIER = "exit-module/0";

    private ExitAction action;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        action = new ExitAction();
    }

    /**
     * Checks that the identifier is returned correctly.
     */
    @Test
    void getIdentifierTest() {
        assertThat(action.getIdentifier()).isEqualTo(IDENTIFIER);
    }

    /**
     * Checks that the toString is correct.
     */
    @Test
    void toStringTest() {
        assertThat(action.toString()).isEqualTo(IDENTIFIER);
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
        ExitAction other = new ExitAction();
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
}
