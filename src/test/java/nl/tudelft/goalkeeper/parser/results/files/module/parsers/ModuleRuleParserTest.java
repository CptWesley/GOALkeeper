package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import krTools.parser.SourceInfo;
import languageTools.program.agent.actions.Action;
import languageTools.program.agent.actions.ActionCombo;
import languageTools.program.agent.msc.MentalLiteral;
import languageTools.program.agent.msc.MentalStateCondition;
import languageTools.program.agent.rules.ListallDoRule;
import languageTools.program.agent.rules.Rule;
import languageTools.program.agent.selector.Selector;
import nl.tudelft.goalkeeper.checking.violations.source.BlockSource;
import nl.tudelft.goalkeeper.checking.violations.source.LineSource;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.files.module.RuleType;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import swiprolog.language.PrologCompound;
import swiprolog.language.PrologQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the RuleParser class.
 */
class ModuleRuleParserTest {

    private static final String FILE_NAME = "fgdssfgd";
    private static int STARTING_LINE = 45;
    private static int ENDING_LINE = 47;

    private Rule rule;
    private List<MentalLiteral> conditions;
    private List<Action<?>> actions;
    private PrologCompound compound;
    private PrologQuery var;

    private RuleParser parser;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        parser = new RuleParser();
        conditions = new ArrayList<>();
        actions = new ArrayList<>();
        rule = Mockito.mock(ListallDoRule.class);
        MentalStateCondition msc = Mockito.mock(MentalStateCondition.class);
        ActionCombo ac = Mockito.mock(ActionCombo.class);
        Mockito.when(msc.getAllLiterals()).thenReturn(conditions);
        Mockito.when(ac.getActions()).thenReturn(actions);
        Mockito.when(rule.getCondition()).thenReturn(msc);
        Mockito.when(rule.getAction()).thenReturn(ac);

        var = Mockito.mock(PrologQuery.class);
        compound = Mockito.mock(PrologCompound.class);
        Mockito.when(var.getCompound()).thenReturn(compound);
        Mockito.when(compound.iterator()).thenReturn(Collections.emptyIterator());
        Mockito.when(compound.getSignature()).thenReturn("");
    }

    /**
     * Checks that the type is parsed correctly.
     */
    @Test
    void getTypeListAllTest() {
        assertThat(parser.parse(rule).getType()).isEqualTo(RuleType.LISTALL);
    }

    /**
     * Checks that we can parse a prolog condition clause.
     */
    @Test
    void prologConditionTest() {
        MentalLiteral lit = Mockito.mock(MentalLiteral.class);
        Mockito.when(lit.getFormula()).thenReturn(var);
        Mockito.when(lit.getOperator()).thenReturn("percept");
        Selector selector = Mockito.mock(Selector.class);
        Mockito.when(lit.getSelector()).thenReturn(selector);
        Mockito.when(selector.getParameters()).thenReturn(new ArrayList<>());
        assertThat(parser.parse(rule).getConditions()).hasSize(0);
        conditions.add(lit);
        assertThat(parser.parse(rule).getConditions()).hasSize(1);
        conditions.add(lit);
        assertThat(parser.parse(rule).getConditions()).hasSize(2);
        assertThat(parser.parse(rule).getKRLanguage()).isEqualTo(KRLanguage.PROLOG);
    }

    /**
     * Checks that we can parse an action.
     */
    @Test
    void actionTest() {
        Action action = Mockito.mock(Action.class);
        Mockito.when(action.getParameters()).thenReturn(Collections.singletonList(var));
        Selector selector = Mockito.mock(Selector.class);
        Mockito.when(selector.getParameters()).thenReturn(new ArrayList<>());
        Mockito.when(action.getSignature()).thenReturn("exit-module/0");
        assertThat(parser.parse(rule).getActions()).hasSize(0);
        actions.add(action);
        assertThat(parser.parse(rule).getActions()).hasSize(1);
        actions.add(action);
        assertThat(parser.parse(rule).getActions()).hasSize(2);
    }

    /**
     * Checks that we can retrieve the source correctly.
     */
    @Test
    @SuppressWarnings("PMD")
    void sourceTest() {
        Action action = Mockito.mock(Action.class);
        Mockito.when(action.getParameters()).thenReturn(Collections.singletonList(var));
        Selector selector = Mockito.mock(Selector.class);
        Mockito.when(selector.getParameters()).thenReturn(new ArrayList<>());
        Mockito.when(action.getSignature()).thenReturn("exit-module/0");
        SourceInfo asi = Mockito.mock(SourceInfo.class);
        Mockito.when(asi.getSource()).thenReturn(FILE_NAME);
        Mockito.when(asi.getLineNumber()).thenReturn(ENDING_LINE);
        Mockito.when(action.getSourceInfo()).thenReturn(asi);
        actions.add(action);
        Source source = parser.parse(rule).getSource();
        assertThat(source.getFile()).isEqualTo(FILE_NAME);
        assertThat(source).isInstanceOf(LineSource.class);
        assertThat(((LineSource) source).getLine()).isEqualTo(ENDING_LINE);
        MentalLiteral lit = Mockito.mock(MentalLiteral.class);
        Mockito.when(lit.getFormula()).thenReturn(var);
        Mockito.when(lit.getOperator()).thenReturn("percept");
        Mockito.when(lit.getSelector()).thenReturn(selector);
        SourceInfo csi = Mockito.mock(SourceInfo.class);
        Mockito.when(csi.getSource()).thenReturn(FILE_NAME);
        Mockito.when(csi.getLineNumber()).thenReturn(STARTING_LINE);
        Mockito.when(lit.getSourceInfo()).thenReturn(csi);
        conditions.add(lit);
        source = parser.parse(rule).getSource();
        assertThat(source.getFile()).isEqualTo(FILE_NAME);
        assertThat(source).isInstanceOf(BlockSource.class);
        assertThat(((BlockSource) source).getStartingLine()).isEqualTo(STARTING_LINE);
        assertThat(((BlockSource) source).getEndingLine()).isEqualTo(ENDING_LINE);
    }
}
