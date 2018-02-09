package nl.tudelft.goalkeeper.parser.results.files.module.details;

import lombok.Getter;

/**
 * Class containing data on the exit condition of a module.
 */
public final class ExitCondition extends ModuleDetail {

    @Getter private ExitConditionType type;

    /**
     * Creates a new exit condition instance.
     * @param type Type of the exit condition.
     */
    public ExitCondition(ExitConditionType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("exit=%s.", type.getName());
    }
}
