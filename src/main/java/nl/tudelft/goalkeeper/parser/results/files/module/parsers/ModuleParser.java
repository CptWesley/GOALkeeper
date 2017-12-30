package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import languageTools.program.agent.Module;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import java.io.IOException;

/**
 * Class that parses module files.
 */
public final class ModuleParser {

    /**
     * Prevents instantiation.
     */
    private ModuleParser() { }

    /**
     * Parses a GOAL module to a GOALkeeper module.
     * @param m Module to parse.
     * @return GOALkeeper module.
     * @throws IOException Thrown when there is a problem reading the file.
     */
    public static ModuleFile parse(Module m) throws IOException {
        ModuleFile module = new ModuleFile(m.getSourceFile().toString());

        for (languageTools.program.agent.rules.Rule r : m.getRules()) {
            module.addRule(RuleParser.parse(r));
        }

        return module;
    }
}
