package nl.tudelft.goalkeeper.parser.results.files.module.actions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.files.module.SubModule;
import nl.tudelft.goalkeeper.parser.results.parts.Sourceable;

/**
 * Class which represents submodule calls.
 */
@EqualsAndHashCode
public final class SubModuleAction implements Action, Sourceable {

    @Getter private SubModule module;
    @Getter @Setter private Source source;

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
