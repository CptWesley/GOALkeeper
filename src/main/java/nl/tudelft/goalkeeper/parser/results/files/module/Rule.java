package nl.tudelft.goalkeeper.parser.results.files.module;

import lombok.Getter;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.Condition;
import nl.tudelft.goalkeeper.parser.results.parts.Literal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Rule class.
 */
public class Rule {

    @Getter private RuleType type;
    private List<Condition> conditions;
    private List<Literal> actions;

    /**
     * Creates a rule instance.
     * @param type Type of rule.
     */
    public Rule(RuleType type) {
        this.type = type;
        this.conditions = new ArrayList<>();
        this.actions = new ArrayList<>();
    }

    /**
     * Gets the condition of this rule.
     * @return Condition of this rule.
     */
    public List<Condition> getConditions() {
        return Collections.unmodifiableList(conditions);
    }

    /**
     * Gets the action of this rule.
     * @return Action of this rule.
     */
    public List<Literal> getActions() {
        return Collections.unmodifiableList(actions);
    }

    /**
     * Adds a condition to the set of conditions
     * @param condition Condition to be added.
     * @return Current object.
     */
    public Rule addCondition(Condition condition) {
        conditions.add(condition);
        return this;
    }

    /**
     * Adds a action to the set of actions
     * @param action Action to be added.
     * @return Current object.
     */
    public Rule addAction(Literal action) {
        actions.add(action);
        return this;
    }

}
