package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Constant;
import nl.tudelft.goalkeeper.parser.results.parts.Function;
import nl.tudelft.goalkeeper.parser.results.parts.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConditionComparatorTest {

    private ConditionComparator comparator;

    @BeforeEach
    void setup() {
        comparator = new ConditionComparator();
    }


    @Test
    void equals() {
        Condition o1 = new AGoalCondition(new Constant("test"));
        Condition o2 = new AGoalCondition((new Constant("test")));
        int comp = comparator.compare(o1, o2);
        assertThat(comp).isEqualTo(0);
    }

    @Test
    void less() {
        Condition o1 = new AGoalCondition(new Constant("test"));
        Condition o2 = new GoalCondition(new Constant("test1"));
        int comp = comparator.compare(o1, o2);
        assertThat(comp).isLessThan(0);
    }

    @Test
    void more() {
        Condition o1 = new GoalCondition(new Constant("test1"));
        Condition o2 = new AGoalCondition(new Constant("test"));
        int comp = comparator.compare(o1, o2);
        assertThat(comp).isGreaterThan(0);
    }

    @Test
    void sameVariable() {
        Variable var = new Variable("test");
        Condition o1 = new AGoalCondition(var);
        Condition o2 = new GoalCondition(var);
        int comp = comparator.compare(o1, o2);

        assertThat(comp).isGreaterThan(0);
    }

    @Test
    void notVariable() {
        Function func = new Function("test");
        Condition o1 = new AGoalCondition(func);
        Condition o2 = new AGoalCondition(func);
        assertThat(comparator.compare(o1, o2)).isEqualTo(0);
    }

    @Test
    void firstVariable() {
        Function function = new Function("test");
        Variable variable = new Variable("test1");
        Condition o1 = new AGoalCondition(function);
        Condition o2 = new AGoalCondition(variable);
        assertThat(comparator.compare(o2, o1)).isNotEqualTo(0);
    }

    @Test
    void secondVariable() {
        Function function = new Function("test");
        Variable variable = new Variable("test1");
        Condition o1 = new AGoalCondition(function);
        Condition o2 = new AGoalCondition(variable);
        assertThat(comparator.compare(o1, o2)).isNotEqualTo(0);
    }
}
