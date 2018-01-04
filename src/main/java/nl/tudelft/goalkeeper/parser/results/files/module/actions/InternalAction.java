package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;

/**
 * Contains data for update actions like insert, delete, adopt, drop.
 */
@EqualsAndHashCode
public class InternalAction implements Action {

    @Getter private InternalActionType type;
    @Getter private Expression expression;

    /**
     * Creates an object instance representing an update action.
     * @param type Update type of the object (e.g. insert, delete, adopt, drop).
     * @param expression Expression which to update.
     */
    public InternalAction(InternalActionType type, Expression expression) {
        this.type = type;
        this.expression = expression;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdentifier() {
        return type.getIdentifier();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s(%s)", getIdentifier(), getExpression());
    }
}
