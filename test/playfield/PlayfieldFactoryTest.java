package playfield;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Marek Řezáč
 */
public class PlayfieldFactoryTest {

    public PlayfieldFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        System.out.println("create");
        String fileName = "C:\\Users\\Marek\\Factory\\NetBeansProjects\\SudokuSolver\\Puzzles\\first.csv";
        Playfield expResult = null;
        Playfield result = PlayfieldFactory.create(fileName);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
