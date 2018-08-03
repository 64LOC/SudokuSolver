package rule;

import java.util.Collection;
import java.util.LinkedList;
import playfield.cellgroup.CellGroup;

public abstract class Rule {

    protected final RuleManager ruleManager;
    protected final LinkedList<CellGroup> cellGroups;

    public Rule(RuleManager ruleManager, Collection<? extends CellGroup> groups) {
        this.ruleManager = ruleManager;
        cellGroups = new LinkedList<>();
        cellGroups.addAll(groups);
    }

    public void addCellGroups(Collection<? extends CellGroup> groups) {
        cellGroups.addAll(groups);
    }
}
