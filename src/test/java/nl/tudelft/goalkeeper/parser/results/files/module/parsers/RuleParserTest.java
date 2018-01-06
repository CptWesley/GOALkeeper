package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import jpl.Term;
import languageTools.program.agent.actions.Action;
import languageTools.program.agent.actions.ActionCombo;
import languageTools.program.agent.msc.MentalLiteral;
import languageTools.program.agent.msc.MentalStateCondition;
import languageTools.program.agent.rules.ListallDoRule;
import languageTools.program.agent.rules.Rule;
import languageTools.program.agent.selector.Selector;
import nl.tudelft.goalkeeper.parser.results.files.module.RuleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import swiprolog.language.PrologQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the RuleParser class.
 */
class RuleParserTest {

    private Rule rule;
    private List<MentalLiteral> conditions;
    private List<Action<?>> actions;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        conditions = new ArrayList<>();
        actions = new ArrayList<>();
        rule = Mockito.mock(ListallDoRule.class);
        MentalStateCondition msc = Mockito.mock(MentalStateCondition.class);
        ActionCombo ac = Mockito.mock(ActionCombo.class);
        Mockito.when(msc.getAllLiterals()).thenReturn(conditions);
        Mockito.when(ac.getActions()).thenReturn(actions);
        Mockito.when(rule.getCondition()).thenReturn(msc);
        Mockito.when(rule.getAction()).thenReturn(ac);
    }

    /**
     * Checks that the type is parsed correctly.
     */
    @Test
    void getTypeListAllTest() {
        assertThat(RuleParser.parse(rule).getType()).isEqualTo(RuleType.LISTALL);
    }

    /**
     * Checks that we can parse a prolog condition clause.
     */
    @Test
    void prologConditionTest() {
        MentalLiteral lit = Mockito.mock(MentalLiteral.class);
        PrologQuery var = Mockito.mock(PrologQuery.class);
        Mockito.when(lit.getFormula()).thenReturn(var);
        Term term = Mockito.mock(Term.class);
        Mockito.when(var.getTerm()).thenReturn(term);
        Mockito.when(term.isVariable()).thenReturn(true);
        Mockito.when(lit.getOperator()).thenReturn("percept");
        Selector selector = Mockito.mock(Selector.class);
        Mockito.when(lit.getSelector()).thenReturn(selector);
        Mockito.when(selector.getParameters()).thenReturn(new ArrayList<>());
        assertThat(RuleParser.parse(rule).getConditions()).hasSize(0);
        conditions.add(lit);
        assertThat(RuleParser.parse(rule).getConditions()).hasSize(1);
        conditions.add(lit);
        assertThat(RuleParser.parse(rule).getConditions()).hasSize(2);
    }

    /**
     * Checks that we can parse an action.
     */
    @Test
    void actionTest() {
        Action action = Mockito.mock(Action.class);
        PrologQuery var = Mockito.mock(PrologQuery.class);
        Mockito.when(action.getParameters()).thenReturn(Collections.singletonList(var));
        Term term = Mockito.mock(Term.class);
        Mockito.when(var.getTerm()).thenReturn(term);
        Mockito.when(term.isVariable()).thenReturn(true);
        Selector selector = Mockito.mock(Selector.class);
        Mockito.when(selector.getParameters()).thenReturn(new ArrayList<>());
        Mockito.when(action.getSignature()).thenReturn("exit-module/0");
        assertThat(RuleParser.parse(rule).getActions()).hasSize(0);
        actions.add(action);
        assertThat(RuleParser.parse(rule).getActions()).hasSize(1);
        actions.add(action);
        assertThat(RuleParser.parse(rule).getActions()).hasSize(2);
    }
}
