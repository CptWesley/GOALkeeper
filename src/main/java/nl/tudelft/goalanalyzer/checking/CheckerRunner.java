package nl.tudelft.goalanalyzer.checking;

import nl.tudelft.goalanalyzer.checking.checkers.CheckerInterface;
import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.rules.RuleSet;
import nl.tudelft.goalanalyzer.util.console.ProgressBar;
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
     * @param files Files to check.
     * @param ruleSet RuleSet to check against.
     * @return Collection of all violations.
     */
    public Collection<Violation> run(String[] files, RuleSet ruleSet) {
        List<Violation> violations = new ArrayList<>();
        Set<Class<?>> classes
                = new Reflections("nl.tudelft.goalanalyzer.checking.checkers")
                .getTypesAnnotatedWith(Checker.class);
        System.out.println("Analyzing using " + classes.size() + " amount of tests");
        ProgressBar progressBar = new ProgressBar(classes.size());

        for (Class c : classes) {
            progressBar.update();
            CheckerInterface checker;
            try {
                checker = (CheckerInterface) c.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            violations.addAll(checker.run(files, ruleSet));
        }
        return violations;
    }
}
