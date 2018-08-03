package rule;

import java.util.LinkedList;
import java.util.List;
import playfield.Cell;

public class RuleManager {

    private final List<AssigningRule> assigningRules;
    private final List<RefiningRule> refiningRules;
    private boolean hintsFound;

    public RuleManager() {
        assigningRules = new LinkedList<>();
        refiningRules = new LinkedList<>();
    }

    public void addAssigningRule(AssigningRule rule) {
        assigningRules.add((AssigningRule) rule);
    }

    public void addRefiningRule(RefiningRule rule) {
        refiningRules.add(rule);
    }

    public void applyRules() {
        hintsFound = false;

        for (AssigningRule assigningRule : assigningRules) {
            applyRefiningRules();

            Cell cellToAssign = assigningRule.findCellToAssign();
            if (cellToAssign != null)
                cellToAssign.setAssigned();
        }

    }

    private void applyRefiningRules() {
        for (RefiningRule refiningRule : refiningRules)
            refiningRule.refineCandidates();
    }

    public void setHintsFound() {
        hintsFound = true;
    }

    public boolean hintsFound() {
        return hintsFound;
    }
}
