package nl.tudelft.goalkeeper.checking;

import nl.tudelft.goalkeeper.checking.checkers.CheckerInterface;
import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.parser.Parser;
import nl.tudelft.goalkeeper.parser.results.ParseResult;
import nl.tudelft.goalkeeper.rules.RuleSet;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Class which is able to run all checkers.
 */
public final class CheckerRunner {

    /**
     * Runs all checkers.
     * @param masPath Path to the mas program to check.
     * @param ruleSet RuleSet to check against.
     * @return Collection of all violations.
     */
    public Collection<Violation> run(String masPath, RuleSet ruleSet) {
        List<Violation> violations = new ArrayList<>();
        Parser parser = new Parser(masPath);
        ParseResult parseResult = parser.parse();
        violations.addAll(parseResult.getViolations());
        if (parseResult.isSuccessful()) {
            Set<Class<?>> classes
                    = new Reflections("nl.tudelft.goalkeeper.checking.checkers")
                    .getTypesAnnotatedWith(Checker.class);
            for (Class c : classes) {
                CheckerInterface checker;
                try {
                    checker = (CheckerInterface) c.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
                violations.addAll(checker.run(parseResult, ruleSet));
            }
        }
        return violations;
    }
}
