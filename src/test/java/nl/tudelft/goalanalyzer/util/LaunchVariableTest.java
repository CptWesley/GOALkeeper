package nl.tudelft.goalanalyzer.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the LaunchVariable class.
 */
class LaunchVariableTest {

    /**
     * Checks that the getAsString method doesn't alter the value.
     */
    @Test
    void stringTest() {
        LaunchVariable var = new LaunchVariable("BLA");
        assertThat(var.getAsString()).isEqualTo("BLA");
    }

    /**
     * Checks that the getAsString method doesn't alter the value.
     */
    @Test
    void booleanTest() {
        assertThat(new LaunchVariable("true").getAsBoolean()).isTrue();
        assertThat(new LaunchVariable("yes").getAsBoolean()).isTrue();
        assertThat(new LaunchVariable("1").getAsBoolean()).isTrue();
        assertThat(new LaunchVariable("false").getAsBoolean()).isFalse();
        assertThat(new LaunchVariable("fgdsu").getAsBoolean()).isFalse();
    }

    /**
     * Checks that the getAsString method doesn't alter the value.
     */
    @Test
    void integerTest() {
        assertThat(new LaunchVariable("56").getAsInteger()).isEqualTo(56);
        assertThat(new LaunchVariable("fdgs").getAsInteger()).isEqualTo(-1);
    }
}
