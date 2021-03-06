package nl.tudelft.goalkeeper.parser.results.files.module;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.files.module.actions.Action;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.Condition;
import nl.tudelft.goalkeeper.parser.results.files.module.conditions.ConditionComparator;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;
import nl.tudelft.goalkeeper.parser.results.parts.Linguistic;
import nl.tudelft.goalkeeper.parser.results.parts.Sourceable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ModuleRule class.
 */
public final class ModuleRule implements Sourceable, Linguistic {

    @Getter private RuleType type;
    private List<Condition> conditions;
    private List<Action> actions;
    @Getter @Setter private Source source;
    @Getter @Setter private KRLanguage kRLanguage = KRLanguage.UNKNOWN;


    /**
     * Creates a rule instance.
     *
     * @param type Type of rule.
     */
    public ModuleRule(RuleType type) {
        this.type = type;
        this.conditions = new ArrayList<>();
        this.actions = new ArrayList<>();
    }

    /**
     * Gets the condition of this rule.
     *
     * @return Condition of this rule.
     */
    public List<Condition> getConditions() {
        return Collections.unmodifiableList(conditions);
    }

    /**
     * Gets the action of this rule.
     *
     * @return Action of this rule.
     */
    public List<Action> getActions() {
        return Collections.unmodifiableList(actions);
    }

    /**
     * Adds a condition to the set of conditions.
     *
     * @param condition Condition to be added.
     * @return Current object.
     */
    public ModuleRule addCondition(Condition condition) {
        conditions.add(condition);
        return this;
    }

    /**
     * Adds a action to the set of actions.
     *
     * @param action Action to be added.
     * @return Current object.
     */
    public ModuleRule addAction(Action action) {
        actions.add(action);
        return this;
    }

    /**
     * Gets the hashcode of the condition collection.
     *
     * @return Hashcode of the conditions.
     */
    public int getConditionsHashCode() {
        int result = 0;
        for (int i = 0; i < conditions.size(); ++i) {
            result += conditions.get(i).hashCode() * (i + 1);
        }
        return result;
    }

    /**
     * Gets the hashcode of the action collection.
     *
     * @return Hashcode of the actions.
     */
    public int getActionsHashCode() {
        int result = 0;
        for (int i = 0; i < actions.size(); ++i) {
            result += actions.get(i).hashCode() * (i + 1);
        }
        return result;
    }

    /**
     * This checks if two rules are equivalent.
     *
     * @param rule The rule to check against.
     * @return True if they are equivalent else false.
     */
    public boolean equivalentTo(ModuleRule rule) {
        if (this.type.equals(rule.type)) {
            List<Condition> r1;
            List<Condition> r2;
            r1 = this.conditions;
            r1.sort(new ConditionComparator());
            r2 = rule.conditions;
            r2.sort(new ConditionComparator());
            return r1.equals(r2);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return (getConditionsHashCode() + getActionsHashCode()) * (type.ordinal() + 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ModuleRule) {
            ModuleRule that = (ModuleRule) o;
            if (!this.type.equals(that.type)) {
                return false;
            }
            if (this.conditions.size() != that.conditions.size()) {
                return false;
            }
            if (this.actions.size() != that.actions.size()) {
                return false;
            }
            for (int i = 0; i < this.conditions.size(); ++i) {
                if (!this.conditions.get(i).equals(that.conditions.get(i))) {
                    return false;
                }
            }
            for (int i = 0; i < this.actions.size(); ++i) {
                if (!this.actions.get(i).equals(that.actions.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type.getPrefix()).append(' ');
        if (conditions.size() == 0) {
            sb.append("true");
        }
        for (int i = 0; i < conditions.size(); ++i) {
            sb.append(conditions.get(i));
            if (i < conditions.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(' ').append(type.getSuffix()).append(' ');
        for (int i = 0; i < actions.size(); ++i) {
            sb.append(actions.get(i));
            if (i < actions.size() - 1) {
                sb.append(" + ");
            }
        }
        sb.append('.');
        return sb.toString();
    }

}
