package nl.tudelft.goalkeeper.rules;


import lombok.Getter;

/**
 * Class that represents a stage.
 */
public final class Stage {
    @Getter private int severity; // NOPMD
    @Getter private double limit; // NOPMD

    /**
     * Constructor for a class that represents a stage.
     */
    public Stage() {
        severity = 0;
        limit = 0;
    }

    /**
     * Sets the severity of the stage.
     * @param value New severity value.
     * @return The current stage.
     */
    public Stage setSeverity(int value) {
        severity = value;
        return this;
    }

    /**
     * Sets the minimum value of the stage.
     * @param value New minimum value of the stage.
     * @return The current stage.
     */
    public Stage setLimit(double value) {
        limit = value;
        return this;
    }
}
