package nl.tudelft.goalanalyzer.rules;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Data class containing rule info.
 */
class Rule {

    private List<Stage> stages;
    @Getter @Setter private boolean enabled; // NOPMD

    /**
     * Constructor of a rule instance.
     */
    Rule() {
        stages = new ArrayList<>();
    }

    /**
     * Returns a copy of the stages contained in this rule.
     * @return Stages contained in this rule.
     */
    List<Stage> getStages() {
        return new ArrayList<>(stages);
    }

    /**
     * Adds a stage to the stage list.
     * @param stage New stage to add.
     * @return Current Rule.
     */
    Rule addStage(Stage stage) {
        if (!stages.contains(stage)) {
            stages.add(stage);
        }
        return this;
    }

    /**
     * Gets the severity of a certain value.
     * @param value Value to check for.
     * @return Severity level of the value.
     */
    int severityOf(double value) {
        for (Stage stage : stages) {
            if (value >= stage.getMin() && value < stage.getMax()) {
                return stage.getSeverity();
            }
        }
        return 0;
    }
}
