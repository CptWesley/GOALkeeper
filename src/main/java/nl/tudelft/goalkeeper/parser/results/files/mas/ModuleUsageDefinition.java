package nl.tudelft.goalkeeper.parser.results.files.mas;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import nl.tudelft.goalkeeper.parser.results.parts.Sourceable;

/**
 * Class representing module use cases.
 */
public final class ModuleUsageDefinition implements Sourceable {

    @Getter private String target;
    @Getter @Setter private ModuleFile module;
    @Getter @Setter private Source source;

    /**
     * Creates a new ModuleUsageDefinitionInstance.
     * @param target File path of the target module.
     */
    public ModuleUsageDefinition(String target) {
        this.target = target;
    }
}
