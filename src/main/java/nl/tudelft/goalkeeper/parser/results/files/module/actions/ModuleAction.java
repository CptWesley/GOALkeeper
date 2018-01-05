package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class representing module calls.
 */
@EqualsAndHashCode
public final class ModuleAction implements Action {

    private List<Expression> arguments;
    @Getter private String target;
    @Getter @Setter private ModuleFile module;

    /**
     * Creates a new module call action instance.
     * @param target Target module source path.
     * @param arguments Arguments given to the call.
     */
    public ModuleAction(String target, Collection<Expression> arguments) {
        this.target = target;
        this.arguments = new LinkedList<>();
        this.arguments.addAll(arguments);
    }

    /**
     * Gets a list of all arguments passed to the module.
     * @return List of all arguments passed to the module.
     */
    public List<Expression> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdentifier() {
        String name;
        if (module == null) {
            name = "null";
        } else {
            name = module.getName();
        }
        return name + "/" + arguments.size();
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
