package nl.tudelft.goalkeeper.rules;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Data class containing rule info.
 */
public class Rule {

    private List<Stage> stages;
    @Getter private boolean enabled; // NOPMD

    /**
     * Constructor of a rule instance.
     */
    public Rule() {
        enabled = true;
        stages = new ArrayList<>();
    }

    /**
     * Returns a copy of the stages contained in this rule.
     * @return Stages contained in this rule.
     */
    public List<Stage> getStages() {
        return Collections.unmodifiableList(stages);
    }

    /**
     * Adds a stage to the stage list.
     * @param stage New stage to add.
     * @return Current Rule.
     */
    public Rule addStage(Stage stage) {
        if (!stages.contains(stage)) {
            stages.add(stage);
        }
        return this;
    }

    /**
     * Sets the status of this rule.
     * @param status Status of this rule.
     * @return This rule.
     */
    public Rule setEnabled(boolean status) {
        enabled = status;
        return this;
    }

    /**
     * Gets the severity of a certain value.
     * @param value Value to check for.
     * @return Severity level of the value.
     */
    public int severityOf(double value) {
        int severity = 0;
        for (Stage stage : stages) {
            if (value >= stage.getLimit()) {
                severity = stage.getSeverity();
            }
        }
        return severity;
    }

    /**
     * Gets the maximum value before a certain severity.
     * @param severity Severity to check for.
     * @return The maximum value before we reach a certain severity.
     */
    public double maxValueBefore(int severity) {
        double max = Double.MAX_VALUE;
        for (Stage stage : stages) {
            if (stage.getSeverity() == severity) {
                max = stage.getLimit();
            }
        }
        return max;
    }
}
