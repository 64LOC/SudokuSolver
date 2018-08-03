package rule;

import java.util.Collection;
import java.util.LinkedList;
import playfield.cellgroup.Box;
import playfield.cellgroup.CellGroup;

public class RuleRowClaiming extends RefiningRule {

    public RuleRowClaiming(RuleManager ruleManager, Collection<? extends CellGroup> groups) {
        super(ruleManager, groups);
    }

    @Override
    void refineCandidates() {
        for (CellGroup cellGroup : cellGroups) {
            LinkedList<Integer> candidatesOnlyInOneSubRow = ((Box) cellGroup).findCandidatesOnlyInOneSubRow();
            for (Integer candidate : candidatesOnlyInOneSubRow)
                if (((Box) cellGroup).removeCandidatesForSubRowAndGetCount(candidate) > 0)
                    ruleManager.setHintsFound();
        }
    }
}
