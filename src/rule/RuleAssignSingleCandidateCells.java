package rule;

import java.util.Collection;
import java.util.Iterator;
import playfield.Cell;
import playfield.cellgroup.CellGroup;

public class RuleAssignSingleCandidateCells extends AssigningRule {

    public RuleAssignSingleCandidateCells(RuleManager ruleManager, Collection<? extends CellGroup> groups) {
        super(ruleManager, groups);
    }

    @Override
    public Cell findCellToAssign() {
        Cell cellToAssign = null;

        Iterator<CellGroup> it = cellGroups.iterator();
        while (it.hasNext() && cellToAssign == null)
            cellToAssign = (it.next()).getSingleCandidateCell();

        if (cellToAssign != null)
            ruleManager.setHintsFound();

        return cellToAssign;
    }

}
