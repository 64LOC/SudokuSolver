package playfield;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import playfield.cellgroup.Box;
import playfield.cellgroup.CellGroup;
import playfield.cellgroup.Column;
import playfield.cellgroup.Row;

public class Playfield {

    public static final int COLUMNS = 9;
    public static final int ROWS = 9;
    public static final int BOXES = 9;

    private Cell[][] cells;
    private Row[] rows;
    private Column[] columns;
    private Box[] boxes;

    public Playfield() {
        initializeEmptyPlayfield();
    }

    private void initializeEmptyPlayfield() {
        initializeCells();
        initializeRows();
        initializeColumns();
        initializeBoxes();
    }

    private void initializeCells() {
        cells = new Cell[COLUMNS][ROWS];
        for (int columnIndex = 0; columnIndex < COLUMNS; columnIndex++)
            for (int rowIndex = 0; rowIndex < ROWS; rowIndex++)
                cells[columnIndex][rowIndex] = new Cell();
    }

    private void initializeRows() {
        rows = new Row[ROWS];

        Cell[] preparedRow = new Cell[COLUMNS];
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex++) {
            for (int columnIndex = 0; columnIndex < COLUMNS; columnIndex++)
                preparedRow[columnIndex] = cells[columnIndex][rowIndex];
            rows[rowIndex] = new Row(preparedRow);
        }
    }

    private void initializeColumns() {
        columns = new Column[COLUMNS];

        Cell[] preparedColumn = new Cell[ROWS];
        for (int columnIndex = 0; columnIndex < COLUMNS; columnIndex++) {
            for (int rowIndex = 0; rowIndex < ROWS; rowIndex++)
                preparedColumn[rowIndex] = cells[columnIndex][rowIndex];
            columns[columnIndex] = new Column(preparedColumn);
        }
    }

    private void initializeBoxes() {
        boxes = new Box[BOXES];

        boxes[0] = new Box("00", "10", "20", "01", "11", "21", "02", "12", "22", cells, new Row[]{rows[0], rows[1], rows[2]}, new Column[]{columns[0], columns[1], columns[2]});
        boxes[1] = new Box("30", "40", "50", "31", "41", "51", "32", "42", "52", cells, new Row[]{rows[0], rows[1], rows[2]}, new Column[]{columns[3], columns[4], columns[5]});
        boxes[2] = new Box("60", "70", "80", "61", "71", "81", "62", "72", "82", cells, new Row[]{rows[0], rows[1], rows[2]}, new Column[]{columns[6], columns[7], columns[8]});

        boxes[3] = new Box("03", "13", "23", "04", "14", "24", "05", "15", "25", cells, new Row[]{rows[3], rows[4], rows[5]}, new Column[]{columns[0], columns[1], columns[2]});
        boxes[4] = new Box("33", "43", "53", "34", "44", "54", "35", "45", "55", cells, new Row[]{rows[3], rows[4], rows[5]}, new Column[]{columns[3], columns[4], columns[5]});
        boxes[5] = new Box("63", "73", "83", "64", "74", "84", "65", "75", "85", cells, new Row[]{rows[3], rows[4], rows[5]}, new Column[]{columns[6], columns[7], columns[8]});

        boxes[6] = new Box("06", "16", "26", "07", "17", "27", "08", "18", "28", cells, new Row[]{rows[6], rows[7], rows[8]}, new Column[]{columns[0], columns[1], columns[2]});
        boxes[7] = new Box("36", "46", "56", "37", "47", "57", "38", "48", "58", cells, new Row[]{rows[6], rows[7], rows[8]}, new Column[]{columns[3], columns[4], columns[5]});
        boxes[8] = new Box("66", "76", "86", "67", "77", "87", "68", "78", "88", cells, new Row[]{rows[6], rows[7], rows[8]}, new Column[]{columns[6], columns[7], columns[8]});
    }

    public Collection<? extends CellGroup> getAllCellGroups() {
        LinkedList<CellGroup> listOfGroups = new LinkedList<>();

        listOfGroups.addAll(Arrays.asList(columns));
        listOfGroups.addAll(Arrays.asList(rows));
        listOfGroups.addAll(Arrays.asList(boxes));

        return listOfGroups;

    }

    Cell[][] getCells() {
        return cells;
    }

    @Override
    public String toString() {
        String str = "";

        for (int rowIndex = 0; rowIndex < Playfield.ROWS; rowIndex++) {
            for (int columnIndex = 0; columnIndex < Playfield.COLUMNS; columnIndex++)

                str += cells[columnIndex][rowIndex] + " ";
            str += "\n";
        }

        return str;
    }

    public Collection<? extends CellGroup> getBoxes() {
        return Arrays.asList(boxes);
    }
}
