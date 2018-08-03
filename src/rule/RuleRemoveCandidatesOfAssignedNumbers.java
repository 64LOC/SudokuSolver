package rule;

import java.util.Collection;
import java.util.LinkedList;
import playfield.cellgroup.CellGroup;

public class RuleRemoveCandidatesOfAssignedNumbers extends RefiningRule {

    public RuleRemoveCandidatesOfAssignedNumbers(RuleManager ruleManager, Collection<? extends CellGroup> groups) {
        super(ruleManager, groups);
    }

    @Override
    protected void refineCandidates() {
        LinkedList<Integer> assignedNumbers;
        for (CellGroup cellGroup : cellGroups) {
            assignedNumbers = cellGroup.getAlreadyAssigned();
            if (cellGroup.removeAllNumbersFromCandidatesAndCountRemovals(assignedNumbers) > 0)
                ruleManager.setHintsFound();
        }
    }
}
