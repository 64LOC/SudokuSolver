package rule;

import java.util.Collection;
import java.util.Iterator;
import playfield.Cell;
import playfield.cellgroup.CellGroup;

public class RuleAssignNumberWithOnlyOneCandidate extends AssigningRule {

    public RuleAssignNumberWithOnlyOneCandidate(RuleManager ruleManager, Collection<? extends CellGroup> groups) {
        super(ruleManager, groups);
    }

    @Override
    protected Cell findCellToAssign() {
        Cell cellToAssign = null;

        Iterator<CellGroup> it = cellGroups.iterator();
        while (it.hasNext() && cellToAssign == null)
            cellToAssign = (it.next()).findNumbersWithOnlyOneCandidate();

        if (cellToAssign != null)
            ruleManager.setHintsFound();

        return cellToAssign;
    }

}
