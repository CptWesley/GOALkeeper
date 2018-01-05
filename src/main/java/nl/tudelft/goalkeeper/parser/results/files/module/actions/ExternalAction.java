package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class representing environment actions.
 */
@EqualsAndHashCode
public final class ExternalAction implements Action {

    @Getter @Setter private String target;
    private List<Expression> arguments;

    /**
     * Creates a new environment action call instance.
     * @param target Target actionspec source path.
     * @param arguments Arguments given to the call.
     */
    public ExternalAction(String target, Collection<Expression> arguments) {
        this.target = target;
        this.arguments = new LinkedList<>();
        this.arguments.addAll(arguments);
    }

    /**
     * Gets a list of all arguments passed to the action.
     * @return List of all arguments passed to the action.
     */
    public List<Expression> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdentifier() {
        return "null/" + arguments.size(); //TODO: Replace by proper identifier.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getIdentifier()).append('(');
        for (int i = 0; i < arguments.size(); ++i) {
            sb.append(arguments.get(i));
            if (i < arguments.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}