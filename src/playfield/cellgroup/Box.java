package playfield.cellgroup;

import java.util.Arrays;
import java.util.LinkedList;
import playfield.Cell;

public class Box extends CellGroup {

    private final Row[] rows;
    private final Column[] columns;

    public Box(String s0, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, Cell[][] cells, Row[] rows, Column[] columns) {
        this.cells = new Cell[SIZE];

        this.cells[0] = getCellAtCoordinates(s0, cells);
        this.cells[1] = getCellAtCoordinates(s1, cells);
        this.cells[2] = getCellAtCoordinates(s2, cells);

        this.cells[3] = getCellAtCoordinates(s3, cells);
        this.cells[4] = getCellAtCoordinates(s4, cells);
        this.cells[5] = getCellAtCoordinates(s5, cells);

        this.cells[6] = getCellAtCoordinates(s6, cells);
        this.cells[7] = getCellAtCoordinates(s7, cells);
        this.cells[8] = getCellAtCoordinates(s8, cells);

        this.rows = new Row[3];
        this.columns = new Column[3];

        System.arraycopy(rows, 0, this.rows, 0, rows.length);
        System.arraycopy(columns, 0, this.columns, 0, columns.length);
    }

    private Cell getCellAtCoordinates(String stringVersion, Cell[][] cells) {
        String strg = stringVersion.substring(1, 2);
        int column = Integer.valueOf(stringVersion.substring(0, 1));
        int row = Integer.valueOf(stringVersion.substring(1, 2));

        return cells[column][row];
    }

    public LinkedList<Integer> findCandidatesOnlyInOneSubRow() {
        LinkedList<Integer> candidates = new LinkedList<>();

        LinkedList<Integer> subRowOne = new LinkedList<>();
        subRowOne.addAll(cells[0].getCandidates());
        subRowOne.addAll(cells[1].getCandidates());
        subRowOne.addAll(cells[2].getCandidates());

        LinkedList<Integer> subRowTwo = new LinkedList<>();
        subRowTwo.addAll(cells[3].getCandidates());
        subRowTwo.addAll(cells[4].getCandidates());
        subRowTwo.addAll(cells[5].getCandidates());

        LinkedList<Integer> subRowThree = new LinkedList<>();
        subRowThree.addAll(cells[6].getCandidates());
        subRowThree.addAll(cells[7].getCandidates());
        subRowThree.addAll(cells[8].getCandidates());

        LinkedList<Integer> rowsTwoAndThree = new LinkedList<>();
        rowsTwoAndThree.addAll(subRowTwo);
        rowsTwoAndThree.addAll(subRowThree);

        LinkedList<Integer> rowsOneAndTwo = new LinkedList<>();
        rowsOneAndTwo.addAll(subRowOne);
        rowsOneAndTwo.addAll(subRowTwo);

        LinkedList<Integer> rowsOneAndThree = new LinkedList<>();
        rowsOneAndThree.addAll(subRowOne);
        rowsOneAndThree.addAll(subRowThree);

        subRowOne.removeAll(rowsTwoAndThree);
        subRowTwo.removeAll(rowsOneAndThree);
        subRowThree.removeAll(rowsOneAndTwo);

        candidates.addAll(subRowOne);
        candidates.addAll(subRowTwo);
        candidates.addAll(subRowThree);

        return candidates;
    }

    public int removeCandidatesForSubRowAndGetCount(Integer candidate) {
        int count = 0;
        Cell cellWithCandidate = findCellsWithCandidate(candidate).get(0);

        for (Row row : rows)
            if (row.contains(cellWithCandidate))
                count += row.removeCandidatesOutsideBox(candidate, this);

        return count;
    }

    public int removeCandidatesForSubColumnAndGetCount(Integer candidate) {
        int count = 0;
        Cell cellWithCandidate = findCellsWithCandidate(candidate).get(0);

        for (Column column : columns)
            if (column.contains(cellWithCandidate))
                count += column.removeCandidatesOutsideBox(candidate, this);

        return count;
    }

    public LinkedList<Integer> findCandidatesOnlyInOneSubColumn() {
        LinkedList<Integer> candidates = new LinkedList<>();

        LinkedList<Integer> subColOne = new LinkedList<>();
        subColOne.addAll(cells[0].getCandidates());
        subColOne.addAll(cells[3].getCandidates());
        subColOne.addAll(cells[6].getCandidates());

        LinkedList<Integer> subColTwo = new LinkedList<>();
        subColTwo.addAll(cells[1].getCandidates());
        subColTwo.addAll(cells[4].getCandidates());
        subColTwo.addAll(cells[7].getCandidates());

        LinkedList<Integer> subColThree = new LinkedList<>();
        subColThree.addAll(cells[2].getCandidates());
        subColThree.addAll(cells[5].getCandidates());
        subColThree.addAll(cells[8].getCandidates());

        LinkedList<Integer> columnsTwoAndThree = new LinkedList<>();
        columnsTwoAndThree.addAll(subColTwo);
        columnsTwoAndThree.addAll(subColThree);

        LinkedList<Integer> columnsOneAndTwo = new LinkedList<>();
        columnsOneAndTwo.addAll(subColOne);
        columnsOneAndTwo.addAll(subColTwo);

        LinkedList<Integer> columnsOneAndThree = new LinkedList<>();
        columnsOneAndThree.addAll(subColOne);
        columnsOneAndThree.addAll(subColThree);

        subColOne.removeAll(columnsTwoAndThree);
        subColTwo.removeAll(columnsOneAndThree);
        subColThree.removeAll(columnsOneAndTwo);

        candidates.addAll(subColOne);
        candidates.addAll(subColTwo);
        candidates.addAll(subColThree);

        return candidates;
    }

    public int lineReduction() {
        int solvingSteps = 0;

        LinkedList<LineCellGroup> lineGroups = new LinkedList<>();
        lineGroups.addAll(Arrays.asList(rows));
        lineGroups.addAll(Arrays.asList(columns));

        for (LineCellGroup lineGroup : lineGroups) {
            LinkedList<Integer> candidates = new LinkedList<>();
            candidates = getCandidatesOfLineGroupOnlyInThisBox(lineGroup);
            solvingSteps += removeCandidatesInOtherSubLineGroups(candidates, lineGroup);
        }

        return solvingSteps;
    }

    private LinkedList<Integer> getCandidatesOfLineGroupOnlyInThisBox(LineCellGroup lineGroup) {
        LinkedList<Integer> candidates = lineGroup.getAllCandidates();
        LinkedList<Integer> candidatesOutsideBox = lineGroup.getCandidatesOutsideBox(this);
        candidates.removeAll(candidatesOutsideBox);
        return candidates;
    }

    private int removeCandidatesInOtherSubLineGroups(LinkedList<Integer> candidates, LineCellGroup lineGroup) {
        int solvingSteps = 0;

        for (Cell cell : cells)
            if (!lineGroup.contains(cell))
                solvingSteps += cell.removeCandidates(candidates);

        return solvingSteps;
    }
}
