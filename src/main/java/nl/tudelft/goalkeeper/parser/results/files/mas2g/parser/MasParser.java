package nl.tudelft.goalkeeper.parser.results.files.mas2g.parser;


import languageTools.program.mas.MASProgram;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.Mas2gFile;

import java.io.IOException;

/**
 * Created by Cedric Willekens (4530373) on 2/17/2018.
 */
public final class MasParser {
    private MasParser() { }

    public static Mas2gFile parse(MASProgram mas) throws IOException {
        Mas2gFile result = new Mas2gFile(mas.getSourceFile().toString());
        for (languageTools.program.mas.LaunchRule rule: mas.getLaunchRules()) {
            result.addLaunchRules(LaunchRuleParser.parse(rule));
        }
        result.setEnvironment(mas.getEnvironmentfile());
        return result;
    }
}
