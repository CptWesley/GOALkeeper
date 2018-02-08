package nl.tudelft.goalkeeper.parser.results.files.module.details;

import languageTools.program.agent.Module;
import lombok.Getter;

/**
 * Order types.
 */
public enum OrderType {
    LINEAR("linear"),
    RANDOM("random"),
    LINEARALL("linearall"),
    LINEARRANDOM("linearrandom"),
    LINEARALLRANDOM("linearallrandom"),
    RANDOMALL("randomall");

    @Getter private String name;

    /**
     * Creates an instance of the order types.
     * @param name Name of the condition.
     */
    OrderType(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Gets the proper OrderType based on GOAL RuleEvaluationOrder.
     * @param order RuleEvaluationOrder to convert.
     * @return GOALkeeper OrderType equivalent of the order.
     */
    public static OrderType get(Module.RuleEvaluationOrder order) {
        switch (order) {
            case RANDOM:
                return RANDOM;
            case LINEARALL:
                return LINEARALL;
            case LINEARRANDOM:
                return LINEARRANDOM;
            case LINEARALLRANDOM:
                return LINEARALLRANDOM;
            case RANDOMALL:
                return RANDOMALL;
            default:
                return LINEAR;
        }
    }
}
