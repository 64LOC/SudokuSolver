package rule;

import java.util.Collection;
import playfield.cellgroup.CellGroup;

public class RuleHiddenPair extends RefiningRule {

    public RuleHiddenPair(RuleManager ruleManager, Collection<? extends CellGroup> groups) {
        super(ruleManager, groups);
    }

    @Override
    void refineCandidates() {
        for (CellGroup cellGroup : cellGroups)
            if (cellGroup.findAndRefineHiddenPairs() > 0)
                ruleManager.setHintsFound();
    }

}
