package nl.tudelft.goalkeeper.parser.results.files.module.details;

import lombok.Getter;

/**
 * Class containing data on the evaluation order of a module.
 */
public final class EvaluationOrder extends ModuleDetail {

    @Getter private EvaluationOrderType type;

    /**
     * Creates a new exit condition instance.
     * @param type Type of the evaluation order.
     */
    public EvaluationOrder(EvaluationOrderType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("order=%s.", type.getName());
    }
}
