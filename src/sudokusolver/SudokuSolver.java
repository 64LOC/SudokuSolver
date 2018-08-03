package sudokusolver;

import playfield.Playfield;
import playfield.PlayfieldFactory;
import rule.RuleManager;
import rule.RuleManagerBuilder;

public class SudokuSolver {

    public static void main(String[] args) {

        //       Playfield playfield = PlayfieldFactory.create("C:\\Users\\Marek\\Factory\\NetBeansProjects\\SudokuSolver\\Puzzles\\first.csv");
        Playfield playfield = PlayfieldFactory.create("C:\\Users\\Marek\\Factory\\NetBeansProjects\\SudokuSolver\\Puzzles\\second.csv");
        //Playfield playfield = PlayfieldFactory.create("C:\\Users\\Marek\\Factory\\NetBeansProjects\\SudokuSolver\\Puzzles\\third.csv");
        RuleManager ruleManager = (new RuleManagerBuilder()).build(playfield);

        do {
            System.out.println(playfield + "\n");

            ruleManager.applyRules();
        } while (ruleManager.hintsFound());

        System.out.println("End of game");

    }

}
