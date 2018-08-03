package rule;

import java.util.Collection;
import java.util.LinkedList;
import playfield.cellgroup.Box;
import playfield.cellgroup.CellGroup;

public class RuleColumnClaiming extends RefiningRule {

    public RuleColumnClaiming(RuleManager ruleManager, Collection<? extends CellGroup> groups) {
        super(ruleManager, groups);
    }

    @Override
    void refineCandidates() {
        for (CellGroup cellGroup : cellGroups) {
            LinkedList<Integer> candidatesOnlyInOneSubColumn = ((Box) cellGroup).findCandidatesOnlyInOneSubColumn();
            for (Integer candidate : candidatesOnlyInOneSubColumn)
                if (((Box) cellGroup).removeCandidatesForSubColumnAndGetCount(candidate) > 0)
                    ruleManager.setHintsFound();
        }
    }
}
