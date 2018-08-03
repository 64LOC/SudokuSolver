package playfield;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayfieldFactory {

    public static Playfield create(String fileName) {
        Playfield playfield = new Playfield();
        Cell[][] cells = playfield.getCells();

        parseFileAndInitializeCells(fileName, cells);

        return playfield;
    }

    private static void parseFileAndInitializeCells(String fileName, Cell[][] cells) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int lineNumber = 0; lineNumber < 9; lineNumber++) {
                String line = reader.readLine();
                parseLineAndInitializeLineOfCells(line, cells, lineNumber);
            }
        } catch (IOException ex) {
            Logger.getLogger(PlayfieldFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void parseLineAndInitializeLineOfCells(String line, Cell[][] cells, int lineNumber) {
        List<String> lineElements = Arrays.asList(line.split(","));

        int columnIndex = 0;
        for (String lineElement : lineElements) {
            if (!(lineElement.compareTo("_") == 0))
                cells[columnIndex][lineNumber].setAssigned(Integer.valueOf(lineElement));

            columnIndex++;
        }
    }
}
