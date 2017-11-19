package nl.tudelft.goalanalyzer.util;

/**
 * Class holding launch parameter variables.
 */
public final class LaunchVariable {

    private String value;

    /**
     * Constructor for the launch variable.
     * @param value Value of the variable.
     */
    public LaunchVariable(String value) {
        this.value = value;
    }

    /**
     * Return the value as a string.
     * @return The value as a string.
     */
    public String getAsString() {
        return value;
    }

    /**
     * Return the value as a boolean.
     * @return The value as a boolean.
     */
    public boolean getAsBoolean() {
        if (value.equals("true") || value.equals("1") || value.equals("yes")) {
            return true;
        }
        return false;
    }

    /**
     * Return the value as a boolean.
     * @return The value as a boolean.
     */
    public int getAsInteger() {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
