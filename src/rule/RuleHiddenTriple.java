package rule;

import java.util.Collection;
import playfield.cellgroup.CellGroup;

public class RuleHiddenTriple extends RefiningRule {

    public RuleHiddenTriple(RuleManager ruleManager, Collection<? extends CellGroup> groups) {
        super(ruleManager, groups);
    }

    @Override
    void refineCandidates() {
        for (CellGroup cellGroup : cellGroups)
            if (cellGroup.findAndRefineHiddenTriples() > 0)
                ruleManager.setHintsFound();
    }

}
