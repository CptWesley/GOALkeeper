package nl.tudelft.goalkeeper.parser.results.files.module.details;

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
}
