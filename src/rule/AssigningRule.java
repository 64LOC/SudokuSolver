package rule;

import java.util.Collection;
import playfield.Cell;
import playfield.cellgroup.CellGroup;

public abstract class AssigningRule extends Rule {

    public AssigningRule(RuleManager ruleManager, Collection<? extends CellGroup> groups) {
        super(ruleManager, groups);
    }

    abstract Cell findCellToAssign();
}
