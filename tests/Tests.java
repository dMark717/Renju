import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;
import java.awt.Color;

public class Tests {

    //Test the Board class
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testGetAndSetPlayerTurn() {
        board.setPlayerTurn(2);
        assertEquals(2, board.getPlayerTurn());
    }

    @Test
    public void testGetAndSetTiles() {
        int[][] testTiles = new int[15][15];
        testTiles[0][0] = 1;
        board.setTiles(testTiles);
        assertArrayEquals(testTiles, board.getTiles());
    }

    @Test
    public void testGetAndSetTile() {
        board.setTile(3, 3, 1);
        assertEquals(1, board.getTiles()[3][3]);
    }

    @Test
    public void testGetAndSetHistory() {
        LinkedList<Coordinate> testHistory = new LinkedList<Coordinate>();
        testHistory.add(new Coordinate(0, 0));
        board.setHistory(testHistory);
        assertEquals(testHistory, board.getHistory());
    }

    @Test
    public void testMakeMove() {
        board.makeMove(0, 0, 1);

        assertEquals(board.getTiles()[0][0], 1);
        assertEquals(board.getPlayerTurn(), 2);
        assertEquals(board.getHistory().size(), 1);
    }

    @Test
    public void testUndoMove() {
        board.makeMove(0, 0, 1);
        board.undoMove();

        assertEquals(board.getTiles()[0][0], 0);
        assertEquals(board.getPlayerTurn(), 1);
        assertTrue(board.getHistory().isEmpty());
    }

    //Test the RoundButton class
    @Test
    public void testRoundButtonCreation() {
        RoundButton roundButton = new RoundButton("Test");
        assertNotNull(roundButton);
    }

    @Test
    public void testButtonBackground() {
        RoundButton roundButton = new RoundButton("Test");
        roundButton.setBackground(Color.WHITE);
        assertEquals(Color.WHITE, roundButton.getBackground());
    }

    //Test the settings class
    @Test
    public void testDifficultyToString() {
        Settings.difficulty = 1;
        assertEquals("Könnyű", Settings.difficultyToString());

        Settings.difficulty = 10;
        assertEquals("Közepes", Settings.difficultyToString());

        Settings.difficulty = 100;
        assertEquals("Nehéz", Settings.difficultyToString());
    }

    @Test
    public void testChangeDifficulty() {
        Settings.difficulty = 1;
        Settings.changeDifficulty();
        assertEquals(10, Settings.difficulty);

        Settings.changeDifficulty();
        assertEquals(100, Settings.difficulty);

        Settings.changeDifficulty();
        assertEquals(1, Settings.difficulty);
    }

    //Test the coordinate class
    @Test
    public void testCoordinateCreation() {
        Coordinate coordinate = new Coordinate(1, 2);
        assertEquals(1, coordinate.x);
        assertEquals(2, coordinate.y);
    }


    //Test the Opponent class
    @Test
    public void testOpponentMoveIsWithinBoard() {
        Coordinate coordinate = Opponent.makeMove(board);
        assertTrue(coordinate.x >= 0 && coordinate.x < 15);
        assertTrue(coordinate.y >= 0 && coordinate.y < 15);
    }


}
