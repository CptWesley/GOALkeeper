package nl.tudelft.goalkeeper.parser.results.files.mas2g.parser;

import nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch.LaunchInstruction;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch.LaunchRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public class LaunchRuleParser {

    private LaunchRuleParser() { }

    public static LaunchRule parse(languageTools.program.mas.LaunchRule rule) {
        List<LaunchInstruction> instructions = new ArrayList<>();
        for (languageTools.program.mas.LaunchInstruction instruction: rule.getInstructions()) {
            instructions.add(LaunchInstructionParser.parse(instruction));
        }
        return new LaunchRule(rule.getEntity(), instructions);
    }

}
