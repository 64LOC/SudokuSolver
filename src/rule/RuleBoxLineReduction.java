package rule;

import java.util.Collection;
import playfield.cellgroup.Box;
import playfield.cellgroup.CellGroup;

public class RuleBoxLineReduction extends RefiningRule {

    public RuleBoxLineReduction(RuleManager ruleManager, Collection<? extends CellGroup> groups) {
        super(ruleManager, groups);
    }

    @Override
    void refineCandidates() {

        for (CellGroup cellGroup : cellGroups)
            if (((Box) cellGroup).lineReduction() > 0)
                ruleManager.setHintsFound();

    }
}
