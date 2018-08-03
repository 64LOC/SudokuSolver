package playfield.cellgroup;

import java.util.LinkedList;
import playfield.Cell;

abstract class LineCellGroup extends CellGroup {

    protected LineCellGroup(Cell[] cells) {
        super(cells);
    }

    LinkedList<Integer> getCandidatesOutsideBox(Box box) {
        LinkedList<Integer> candidatesOutsideBox = new LinkedList<>();

        for (Cell cell : cells)
            if (!box.contains(cell) && !cell.isAssigned())
                candidatesOutsideBox.addAll(cell.getCandidates());

        return candidatesOutsideBox;
    }

    int removeCandidatesOutsideBox(Integer candidate, Box box) {
        int count = 0;

        for (Cell cell : cells)
            if (!cell.isAssigned())
                if (!box.contains(cell))
                    count += cell.removeCandidate(candidate);

        return count;
    }
}
