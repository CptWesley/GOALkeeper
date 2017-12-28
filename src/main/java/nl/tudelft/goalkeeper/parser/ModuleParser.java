package nl.tudelft.goalkeeper.parser;

import krTools.language.Expression;
import krTools.language.Update;
import krTools.language.Var;
import languageTools.program.agent.Module;
import languageTools.program.agent.actions.Action;
import languageTools.program.agent.actions.ModuleCallAction;
import languageTools.program.agent.rules.ForallDoRule;
import languageTools.program.agent.rules.ListallDoRule;
import nl.tudelft.goalkeeper.parser.results.files.module.ModuleFile;
import nl.tudelft.goalkeeper.parser.results.files.module.Rule;
import nl.tudelft.goalkeeper.parser.results.files.module.RuleType;
import nl.tudelft.goalkeeper.parser.results.parts.FreeVariable;
import nl.tudelft.goalkeeper.parser.results.parts.Literal;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            module.addRule(parseRule(r));
        }

        return module;
    }

    /**
     * Parses a rule.
     * @param r Rule to parse.
     * @return GOALkeeper rule version of the rule.
     */
    private static Rule parseRule(languageTools.program.agent.rules.Rule r) {
        Rule rule = new Rule(getType(r));
        Map<String, Variable> freeVariables = new HashMap<>();

        for (languageTools.program.agent.msc.MentalLiteral l
                : r.getCondition().getAllLiterals()) {
            rule.addCondition(parseCondition(l, freeVariables));
        }
        for (Action a : r.getAction().getActions()) {
            rule.addAction(parseAction(a, freeVariables));
        }

        return rule;
    }

    /**
     * Gets the type of a rule.
     * @param r Rule to get the type from.
     * @return Type of the rule.
     */
    private static RuleType getType(languageTools.program.agent.rules.Rule r) {
        if (r instanceof ListallDoRule) {
            return RuleType.LISTALL;
        }
        if (r instanceof ForallDoRule) {
            return RuleType.FORALL;
        }
        return RuleType.IF;
    }

    /**
     * Parses a mental literal to a condition literal.
     * @param l Condition to parse.
     * @param freeVariables Map containing the free variables already matched.
     * @return Condition literal.
     */
    private static Literal parseCondition(
            languageTools.program.agent.msc.MentalLiteral l, Map<String, Variable> freeVariables) {
        String signature = l.getFormula().getSignature();
        List<Variable> vars = new ArrayList<>();

        for (Var v : l.getFreeVar()) {
            Variable variable = parseVariable(v, freeVariables);
            freeVariables.put(v.getSignature(), variable);
            vars.add(variable);
        }

        return new Literal(signature, vars.toArray(new Variable[vars.size()]));
    }

    /**
     * Parse an action to an action literal.
     * @param a Action to parse
     * @param freeVariables Map containing the free variables already matched.
     * @return Action literal.
     */
    private static Literal parseAction(Action a, Map<String, Variable> freeVariables) {
        List<Variable> vars = new ArrayList<>();

        System.out.println(a.getClass() + "::" + a.getSignature() + "::" + a.getClass() + "::" + a.getSourceInfo().getSource());

        if (a instanceof ModuleCallAction) {
            ModuleCallAction mc = (ModuleCallAction) a;
            System.out.println(mc.getTarget().getName() + "::" + mc.getTarget().getRules().size());
        }

        for (Object o : a.getParameters()) {
            System.out.println(o + "::" + o.getClass());
            if (o instanceof Update) {
                Update u = (Update) o;
                System.out.println("UPDATE: " + u.getSignature());
            }
        }

        for (Object o : a.getFreeVar()) {
            Var v = (Var) o;
            Variable variable = parseVariable(v, freeVariables);
            freeVariables.put(v.getSignature(), variable);
            vars.add(variable);
        }

        return new Literal(a.getSignature(), vars.toArray(new Variable[vars.size()]));
    }

    /**
     * Parse a variable to an action literal.
     * @param v Variable to parse
     * @param freeVariables Map containing the free variables already matched.
     * @return Parsed variable.
     */
    private static Variable parseVariable(Var v, Map<String, Variable> freeVariables) {
        if (freeVariables.containsKey(v.getSignature())) {
            return freeVariables.get(v.getSignature());
        } else {
            Variable freeVariable = new FreeVariable("Var" + freeVariables.size());
            freeVariables.put(v.getSignature(), freeVariable);
            return freeVariable;
        }
    }
}
