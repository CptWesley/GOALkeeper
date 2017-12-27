package nl.tudelft.goalkeeper.rules;


import lombok.Getter;

/**
 * Class that represents a stage.
 */
class Stage {

    @Getter private int severity; // NOPMD
    @Getter private double min; // NOPMD
    @Getter private double max; // NOPMD
    @Getter private String jar;

    /**
     * Constructor for a class that represents a stage.
     */
    Stage() {
        severity = 0;
        min = Double.MIN_VALUE;
        max = Double.MAX_VALUE;
        jar = "";
    }

    /**
     * Sets the severity of the stage.
     * @param value New severity value.
     * @return The current stage.
     */
    Stage setSeverity(int value) {
        severity = value;
        return this;
    }

    /**
     * Sets the minimum value of the stage.
     * @param value New minimum value of the stage.
     * @return The current stage.
     */
    Stage setMin(double value) {
        min = value;
        return this;
    }

    /**
     * Sets the maximum value of the stage.
     * @param value New maximum value of the stage.
     * @return The current stage.
     */
    Stage setMax(double value) {
        max = value;
        return this;
    }

    Stage setJar(String jar) {
        this.jar = jar;
        return this;
    }
}
