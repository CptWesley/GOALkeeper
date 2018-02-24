package nl.tudelft.goalkeeper.parser.results.files.mas2g.parser;


import languageTools.program.mas.MASProgram;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch.LaunchRule;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.Mas2gFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public final class MasParser {
    public MasParser() { }

    public Mas2gFile parse(MASProgram mas) throws IOException {
        Mas2gFile result = new Mas2gFile(mas.getSourceFile().toString());
        List<LaunchRule> ruleList = new ArrayList<>();
        for (languageTools.program.mas.LaunchRule rule: mas.getLaunchRules()) {
            ruleList.add(LaunchRuleParser.parse(rule));
        }
        result.addLaunchRules(ruleList);
        result.setEnvironment(mas.getEnvironmentfile());
        return result;
    }
}
