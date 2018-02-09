package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import nl.tudelft.goalkeeper.parser.results.parts.Constant;
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
}
