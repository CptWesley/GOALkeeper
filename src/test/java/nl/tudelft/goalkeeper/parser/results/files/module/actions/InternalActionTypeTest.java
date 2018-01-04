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
     * Checks that the canceltimer/1 identifier is returned correctly.
     */
    @Test
    void cancelTimerTest() {
        assertThat(InternalActionType.CANCEL_TIMER.getIdentifier()).isEqualTo("canceltimer/1");
    }

    /**
     * Checks that the starttimer/1 identifier is returned correctly.
     */
    @Test
    void startTimerTest() {
        assertThat(InternalActionType.START_TIMER.getIdentifier()).isEqualTo("starttimer/3");
    }

    /**
     * Checks that the log/1 identifier is returned correctly.
     */
    @Test
    void logTest() {
        assertThat(InternalActionType.LOG.getIdentifier()).isEqualTo("log/1");
    }

    /**
     * Checks that the print/1 identifier is returned correctly.
     */
    @Test
    void printTest() {
        assertThat(InternalActionType.PRINT.getIdentifier()).isEqualTo("print/1");
    }

    /**
     * Checks that the sleep/1 identifier is returned correctly.
     */
    @Test
    void sleepTest() {
        assertThat(InternalActionType.SLEEP.getIdentifier()).isEqualTo("sleep/1");
    }

    /**
     * Checks that the subscribe/1 identifier is returned correctly.
     */
    @Test
    void subscribeTest() {
        assertThat(InternalActionType.SUBSCRIBE.getIdentifier()).isEqualTo("subscribe/1");
    }

    /**
     * Checks that the unsubscribe/1 identifier is returned correctly.
     */
    @Test
    void unsubscribeTest() {
        assertThat(InternalActionType.UNSUBSCRIBE.getIdentifier()).isEqualTo("unsubscribe/1");
    }

    /**
     * Checks that the toString works correctly.
     */
    @Test
    void toStringTest() {
        assertThat(InternalActionType.INSERT.toString()).isEqualTo("insert/1");
    }

    /**
     * Checks that the get function returns the correct types.
     */
    @Test
    void getNonNullTest() {
        assertThat(InternalActionType.get("insert/1")).isSameAs(InternalActionType.INSERT);
    }

    /**
     * Checks that the get function returns null when the type is unknown.
     */
    @Test
    void getNullTest() {
        assertThat(InternalActionType.get("sfdg")).isNull();
    }
}
