package playfield.cellgroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import playfield.Cell;
import rule.subgroups.*;

public abstract class CellGroup {

    protected static int SIZE = 9;
    protected Cell[] cells;
    protected HashMap<Integer, Integer> histogramOfNumbers;

    protected CellGroup() {
    }

    protected CellGroup(Cell[] cells) {
        this.cells = new Cell[SIZE];
        System.arraycopy(cells, 0, this.cells, 0, cells.length);
    }

    public LinkedList<Integer> getAlreadyAssigned() {
        LinkedList<Integer> list = new LinkedList<>();

        for (Cell cell : cells)
            if (cell.isAssigned())
                list.add(cell.getNumber());

        return list;
    }

    public int removeAllNumbersFromCandidatesAndCountRemovals(LinkedList<Integer> assignedNumbers) {
        int removals = 0;

        for (Cell cell : cells)
            if (!cell.isAssigned())
                removals = cell.removeCandidates(assignedNumbers);

        return removals;
    }

    public Cell getSingleCandidateCell() {

        for (Cell cell : cells)
            if (!cell.isAssigned() && cell.getNumberOfCandidates() == 1)
                return cell;

        return null;
    }

    public Cell findNumbersWithOnlyOneCandidate() {
        Cell cellToAssign = null;

        constructHistogramOfNumbers();

        for (HashMap.Entry<Integer, Integer> entry : histogramOfNumbers.entrySet())
            if (entry.getValue().equals(1)) {

                try {
                    ArrayList<Cell> debugCell = findCellsWithCandidate(entry.getKey());
                    debugCell.get(0);
                } catch (java.lang.IndexOutOfBoundsException ex) {
                    System.out.println("tady to je");
                    ArrayList<Cell> debugCell2 = findCellsWithCandidate(entry.getKey());
                    debugCell2.get(0);
                }

                cellToAssign = findCellsWithCandidate(entry.getKey()).get(0);
                cellToAssign.leaveJustOneCandidate(entry.getKey());
            }

        return cellToAssign;
    }

    protected void constructHistogramOfNumbers() {
        initializeHistogramOfNumbers();
        for (Cell cell : cells)
            cell.addCandidatesToHistogramOfNumbers(histogramOfNumbers);
    }

    protected void initializeHistogramOfNumbers() {
        histogramOfNumbers = new HashMap<>();
        for (int i = 1; i < 10; i++)
            histogramOfNumbers.put(i, 0);
    }

    public ArrayList<Cell> findCellsWithCandidate(Integer key) {
        ArrayList<Cell> cellsWithCandidate = new ArrayList<>();

        for (Cell cell : cells)
            if (cell.hasCandidate(key))
                cellsWithCandidate.add(cell);

        return cellsWithCandidate;
    }

    protected boolean contains(Cell testedCell) {
        boolean result = false;

        for (Cell cell : cells)
            if (testedCell == cell)
                result = true;

        return result;
    }

    LinkedList<Integer> getAllCandidates() {
        LinkedList<Integer> allCandidates = new LinkedList<>();
        LinkedList<Integer> cellCandidates;

        for (Cell cell : cells) {
            cellCandidates = (LinkedList<Integer>) cell.getCandidates();
            for (Integer cellCandidate : cellCandidates)
                if (!allCandidates.contains(cellCandidate))
                    allCandidates.add(cellCandidate);
        }

        return allCandidates;
    }

    public int findAndRefineHiddenPairs() {
        PairSet pairs = new PairSet();

        constructHistogramOfNumbers();

        for (HashMap.Entry<Integer, Integer> entry : histogramOfNumbers.entrySet())
            if (entry.getValue().equals(2)) {
                ArrayList<Cell> cellsWithCandidate = findCellsWithCandidate(entry.getKey());
                pairs.add(new Pair(entry.getKey(), cellsWithCandidate.get(0), cellsWithCandidate.get(1)));
            }

        return pairs.findEqualPairsAndRefine();
    }

    public int findAndRefineHiddenTriples() {
        TripleSet triples = new TripleSet();

        constructHistogramOfNumbers();

        for (HashMap.Entry<Integer, Integer> entry : histogramOfNumbers.entrySet())
            if (entry.getValue().equals(3)) {
                ArrayList<Cell> cellsWithCandidate = findCellsWithCandidate(entry.getKey());
                triples.add(new Triple(entry.getKey(), cellsWithCandidate.get(0), cellsWithCandidate.get(1), cellsWithCandidate.get(2)));
            }

        return triples.findEqualTriplesAndRefine();
    }

}
