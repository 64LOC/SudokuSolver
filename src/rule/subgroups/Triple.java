package rule.subgroups;

import playfield.Cell;

public class Triple {

    private final Integer number;
    private final Cell[] cells;

    public Triple(Integer number, Cell cell_0, Cell cell_1, Cell cell_2) {
        this.number = number;
        cells = new Cell[]{cell_0, cell_1, cell_2};
    }

    boolean hasSameCells(Triple comparedTriple) {
        return cells[0].equals(comparedTriple.cells[0]) && cells[1].equals(comparedTriple.cells[1]) && cells[2].equals(comparedTriple.cells[2]);
    }

    int removeCandidatesExceptTriple(Triple firstComparedTriple, Triple secondComparedTriple) {
        int solvingSteps = 0;

        solvingSteps += cells[0].removeCandidatesExcept(number, firstComparedTriple.number, secondComparedTriple.number);
        solvingSteps += cells[1].removeCandidatesExcept(number, firstComparedTriple.number, secondComparedTriple.number);
        solvingSteps += cells[2].removeCandidatesExcept(number, firstComparedTriple.number, secondComparedTriple.number);

        return solvingSteps;
    }
}
