package nl.tudelft.goalkeeper.parser.results.files.module;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.parser.results.files.File;
import nl.tudelft.goalkeeper.parser.results.files.module.details.EvaluationOrder;
import nl.tudelft.goalkeeper.parser.results.files.module.details.EvaluationOrderType;
import nl.tudelft.goalkeeper.parser.results.files.module.details.ExitCondition;
import nl.tudelft.goalkeeper.parser.results.files.module.details.ExitConditionType;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;
import nl.tudelft.goalkeeper.parser.results.parts.Linguistic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class which holds data on a module file.
 */
public final class ModuleFile extends File implements Module, Linguistic {

    private List<ModuleRule> rules;
    @Getter @Setter private String name; //NOPMD PMD can't handle Lombok.
    @Getter @Setter private KRLanguage kRLanguage = KRLanguage.UNKNOWN;
    @Getter @Setter private EvaluationOrder evaluationOrder
            = new EvaluationOrder(EvaluationOrderType.UNKNOWN);
    @Getter @Setter private ExitCondition exitCondition
            = new ExitCondition(ExitConditionType.UNKNOWN);

    /**
     * Creates a new ModuleFile instance.
     * @param fileName File path to parse.
     * @throws IOException Thrown when file could not be read.
     */
    public ModuleFile(String fileName) throws IOException {
        super(fileName);
        this.rules = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ModuleRule> getRules() {
        return Collections.unmodifiableList(rules);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRule(ModuleRule rule) {
        rules.add(rule);
    }

}
