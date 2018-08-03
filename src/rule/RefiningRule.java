package rule;

import java.util.Collection;
import playfield.cellgroup.CellGroup;

abstract public class RefiningRule extends Rule {

    public RefiningRule(RuleManager ruleManager, Collection<? extends CellGroup> groups) {
        super(ruleManager, groups);
    }

    abstract void refineCandidates();
}
