package rule.subgroups;

import playfield.Cell;

public class Pair {

    private final Integer number;
    private final Cell[] cells;

    public Pair(Integer number, Cell cell_0, Cell cell_1) {
        this.number = number;
        cells = new Cell[]{cell_0, cell_1};
    }

    boolean hasSameCells(Pair comparedPair) {
        return cells[0].equals(comparedPair.cells[0]) && cells[1].equals(comparedPair.cells[1]);
    }

    int removeCandidatesExceptPair(Pair comparedPair) {
        int solvingSteps = 0;

        solvingSteps += cells[0].removeCandidatesExcept(number, comparedPair.number);
        solvingSteps += cells[1].removeCandidatesExcept(number, comparedPair.number);

        return solvingSteps;
    }

}
