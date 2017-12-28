package nl.tudelft.goalkeeper.checking.violations;

import lombok.Getter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;

/**
 * Class for violations.
 */
public class Violation {
    private static final int BUILDER_CAPACITY = 30;

    @Getter private String message; //NOPMD
    @Getter private int severity;
    @Getter private Source source; //NOPMD
    @Getter private double actualValue; //NOPMD
    @Getter private double maximumValue; //NOPMD
    @Getter private boolean error; //NOPMD
    @Getter private String suggestion;

    /**
     * Constructor for the violations class.
     * @param message Message of the violations.
     * @param severity Severity of the violation.
     */
    public Violation(String message, int severity) {
        this.message = message;
        this.severity = severity;
        source = null;
        actualValue = -1;
        maximumValue = -1;
        error = false;
        suggestion = "";
    }

    /**
     * Sets the source of the violation.
     * @param source Source of the violation.
     * @return This violation.
     */
    public Violation setSource(Source source) {
        this.source = source;
        return this;
    }

    /**
     * Sets the actual value of the metric.
     * @param value Actual value of the metric.
     * @return This violation.
     */
    public Violation setActualValue(double value) {
        actualValue = value;
        return this;
    }

    /**
     * Sets the maximum value of this metric.
     * @param value Maximum value of this metric.
     * @return This violation.
     */
    public Violation setMaximumValue(double value) {
        maximumValue = value;
        return this;
    }

    /**
     * Sets the error value of this violation.
     * @param status Error status of the violation.
     * @return This violation.
     */
    public Violation setError(boolean status) {
        error = status;
        return this;
    }

    /**
     * Set the suggestion value for this violation.
     * @param suggestion The suggestion for the violation.
     * @return The current violation.
     */
    public Violation setSuggestion(String suggestion) {
        this.suggestion = suggestion;
        return this;
    }

    @SuppressWarnings("MethodLength")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(BUILDER_CAPACITY);
        if (error) {
            sb.append("Error ");
        } else {
            sb.append("Warning ");
        }
        sb.append('\'').append(message).append("' of severity ")
                .append(severity).append(" found");
        if (source != null) {
            sb.append(' ').append(source);
        }
        sb.append('.');
        if (actualValue >= 0 && maximumValue >= 0) {
            sb.append(" Value was: '").append(actualValue)
                    .append("' while maximum is '").append(maximumValue).append("'.");
        }
        if (!this.suggestion.equals("")) {
            sb.append(' ');
            sb.append(this.suggestion);
        }
        return sb.toString();
    }
}
