package nl.tudelft.goalanalyzer.checking.violations;

import lombok.Getter;

/**
 * Class for violations.
 */
public class Violation {
    private static final int BUILDER_CAPACITY = 30;

    @Getter private String message; //NOPMD
    @Getter private int severity;
    @Getter private String file; //NOPMD
    @Getter private int startingLine; //NOPMD
    @Getter private int endingLine; //NOPMD
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
        file = "";
        startingLine = -1;
        endingLine = -1;
        actualValue = -1;
        maximumValue = -1;
        error = false;
        suggestion = "";
    }

    /**
     * Sets the file location of this violation.
     * @param file File location of this violation.
     * @return This violation.
     */
    public Violation setFile(String file) {
        this.file = file;
        return this;
    }

    /**
     * Sets the starting line of this violation.
     * @param line Line number where the violation starts.
     * @return This violation.
     */
    public Violation setStartingLine(int line) {
        startingLine = line;
        return this;
    }

    /**
     * Sets the ending line of this violation.
     * @param line Line number where the violation end.
     * @return This violation.
     */
    public Violation setEndingLine(int line) {
        endingLine = line;
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
     * Set the suggestion value for this violation
     * @param suggestion The suggestion for the violation.
     * @return The current violation.
     */
    public Violation setSuggestion(String suggestion) {
        this.suggestion = suggestion;
        return this;
    }

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
        if (!file.isEmpty()) {
            sb.append(" in '").append(file).append('\'');
        }
        if (startingLine >= 0 && endingLine >= 0) {
            if (startingLine == endingLine) {
                sb.append(" at line ").append(startingLine);
            } else {
                sb.append(" at lines ").append(startingLine)
                        .append('-').append(endingLine);
            }
        }
        sb.append('.');
        if (actualValue >= 0 && maximumValue >= 0) {
            sb.append(" Value was: '").append(actualValue)
                    .append("' while maximum is '").append(maximumValue).append("'.");
        }
        sb.append(' ');
        if (!this.suggestion.equals("")) {
            sb.append(this.suggestion);
        }
        sb.append("\n");
        return sb.toString();
    }
}
