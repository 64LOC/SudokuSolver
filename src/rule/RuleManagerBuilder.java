package rule;

import java.lang.reflect.Constructor;
import java.util.Collection;
import playfield.Playfield;
import playfield.cellgroup.CellGroup;

public class RuleManagerBuilder {

    private RuleManager instance;

    public RuleManager build(Playfield playfield) {
        instance = new RuleManager();

        instance.addAssigningRule((AssigningRule) createRuleForClass(RuleAssignSingleCandidateCells.class, playfield.getAllCellGroups()));
        instance.addAssigningRule((AssigningRule) createRuleForClass(RuleAssignNumberWithOnlyOneCandidate.class, playfield.getAllCellGroups()));

        instance.addRefiningRule((RefiningRule) createRuleForClass(RuleRemoveCandidatesOfAssignedNumbers.class, playfield.getAllCellGroups()));
        instance.addRefiningRule((RefiningRule) createRuleForClass(RuleRowClaiming.class, playfield.getBoxes()));
        instance.addRefiningRule((RefiningRule) createRuleForClass(RuleColumnClaiming.class, playfield.getBoxes()));
        instance.addRefiningRule((RefiningRule) createRuleForClass(RuleBoxLineReduction.class, playfield.getBoxes()));
        instance.addRefiningRule((RefiningRule) createRuleForClass(RuleHiddenPair.class, playfield.getAllCellGroups()));
        instance.addRefiningRule((RefiningRule) createRuleForClass(RuleHiddenTriple.class, playfield.getAllCellGroups()));

        return instance;
    }

    private Rule createRuleForClass(Class classForRule, Collection<? extends CellGroup> groups) {
        Rule createdRule = null;

        Constructor constructor = (classForRule.getDeclaredConstructors())[0];
        try {
            createdRule = (Rule) constructor.newInstance(instance, groups);
        } catch (Exception ex) {
            System.out.println("Can not create rule for class: " + classForRule);
            System.out.println(ex);
            System.exit(1);
        }

        return createdRule;
    }
}
