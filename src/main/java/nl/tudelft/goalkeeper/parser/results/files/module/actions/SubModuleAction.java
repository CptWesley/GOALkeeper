package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import nl.tudelft.goalkeeper.parser.results.files.module.SubModule;

/**
 * Class which represents submodule calls.
 */
@EqualsAndHashCode
public final class SubModuleAction implements Action {

    @Getter private SubModule module;

    /**
     * Creates a new sub module call instance.
     * @param module Sub module being called.
     */
    public SubModuleAction(SubModule module) {
        this.module = module;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdentifier() {
        return "submodule/0";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuilder()
                .append('{')
                .append(System.getProperty("line.separator"))
                .append(module)
                .append(System.getProperty("line.separator"))
                .append('}').toString();
    }
}
