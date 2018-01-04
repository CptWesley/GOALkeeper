package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the InternalActionType enum.
 */
class InternalActionTypeTest {

    /**
     * Checks that the insert/1 identifier is returned correctly.
     */
    @Test
    void insertTest() {
        assertThat(InternalActionType.INSERT.getIdentifier()).isEqualTo("insert/1");
    }

    /**
     * Checks that the delete/1 identifier is returned correctly.
     */
    @Test
    void deleteTest() {
        assertThat(InternalActionType.DELETE.getIdentifier()).isEqualTo("delete/1");
    }

    /**
     * Checks that the adopt/1 identifier is returned correctly.
     */
    @Test
    void adoptTest() {
        assertThat(InternalActionType.ADOPT.getIdentifier()).isEqualTo("adopt/1");
    }

    /**
     * Checks that the drop/1 identifier is returned correctly.
     */
    @Test
    void dropTest() {
        assertThat(InternalActionType.DROP.getIdentifier()).isEqualTo("drop/1");
    }

    /**
     * Checks that the toString works correctly.
     */
    @Test
    void toStringTest() {
        assertThat(InternalActionType.INSERT.toString()).isEqualTo("insert/1");
    }
}
