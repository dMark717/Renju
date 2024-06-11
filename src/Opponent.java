import java.util.Random;
/**
 * Ellenfél
 * Ez az osztály felelős az ellenfél lépéséért
 */
public class Opponent {
    /**
     * Random lépés
     * @param board A tábla
     * @return Egy random lépés
     */
    public static Coordinate randomMove(Board board) {
        Random rand = new Random();
        int x = rand.nextInt(15);
        int y = rand.nextInt(15);
        while (board.getTiles()[x][y] != 0) {
            x = rand.nextInt(15);
            y = rand.nextInt(15);
        }
        return new Coordinate(x, y);
    }
    /**
     * Számított lépés
     * @param board A tábla
     * @return Egy lépés
     */

    public static Coordinate calculatedMove(Board board) {
        // Implementation of opponent decision tree
        //Check if there is 4 in a row
        for (int i = 0; i < 15; i++) {
            for (int j = 1; j < 12; j++) {
                if (board.getTiles()[i][j] != 0 && board.getTiles()[i][j] == board.getTiles()[i][j+1] && board.getTiles()[i][j] == board.getTiles()[i][j+2] && board.getTiles()[i][j] == board.getTiles()[i][j+3]) {
                    if (board.getTiles()[i][j+4] == 0) return new Coordinate(i, j+4);
                    if (board.getTiles()[i][j-1] == 0) return new Coordinate(i, j-1);
                }
            }
        }

        //Check if there is 4 in a column
        for (int i = 1; i < 12; i++) {
            for (int j = 0; j < 15; j++) {
                if (board.getTiles()[i][j] != 0 && board.getTiles()[i][j] == board.getTiles()[i+1][j] && board.getTiles()[i][j] == board.getTiles()[i+2][j] && board.getTiles()[i][j] == board.getTiles()[i+3][j]) {
                    if (board.getTiles()[i+4][j] == 0) return new Coordinate(i+4, j);
                    if (board.getTiles()[i-1][j] == 0) return new Coordinate(i-1, j);
                }
            }
        }

        //Check if there is 4 in a diagonal
        for (int i = 1; i < 12; i++) {
            for (int j = 1; j < 12; j++) {
                if (board.getTiles()[i][j] != 0 && board.getTiles()[i][j] == board.getTiles()[i+1][j+1] && board.getTiles()[i][j] == board.getTiles()[i+2][j+2] && board.getTiles()[i][j] == board.getTiles()[i+3][j+3]) {
                    if (board.getTiles()[i+4][j+4] == 0) return new Coordinate(i+4, j+4);
                    if (board.getTiles()[i-1][j-1] == 0) return new Coordinate(i-1, j-1);
                }
            }
        }

        //Check if there is 4 in a diagonal
        for (int i = 1; i < 12; i++) {
            for (int j = 4; j < 15; j++) {
                if (board.getTiles()[i][j] != 0 && board.getTiles()[i][j] == board.getTiles()[i+1][j-1] && board.getTiles()[i][j] == board.getTiles()[i+2][j-2] && board.getTiles()[i][j] == board.getTiles()[i+3][j-3]) {
                    if (board.getTiles()[i+4][j-4] == 0) return new Coordinate(i+4, j-4);
                    if (board.getTiles()[i-1][j+1] == 0) return new Coordinate(i-1, j+1);
                }
            }
        }

        //Check if there is 3 in a row
        for (int i = 0; i < 15; i++) {
            for (int j = 1; j < 13; j++) {
                if (board.getTiles()[i][j] != 0 && board.getTiles()[i][j] == board.getTiles()[i][j+1] && board.getTiles()[i][j] == board.getTiles()[i][j+2]) {
                    if (board.getTiles()[i][j+3] == 0) return new Coordinate(i, j+3);
                    if (board.getTiles()[i][j-1] == 0) return new Coordinate(i, j-1);
                }
            }
        }

        //Check if there is 3 in a column
        for (int i = 1; i < 13; i++) {
            for (int j = 0; j < 15; j++) {
                if (board.getTiles()[i][j] != 0 && board.getTiles()[i][j] == board.getTiles()[i+1][j] && board.getTiles()[i][j] == board.getTiles()[i+2][j]) {
                    if (board.getTiles()[i+3][j] == 0) return new Coordinate(i+3, j);
                    if (board.getTiles()[i-1][j] == 0) return new Coordinate(i-1, j);
                }
            }
        }

        //Check if there is 3 in a diagonal
        for (int i = 1; i < 13; i++) {
            for (int j = 1; j < 13; j++) {
                if (board.getTiles()[i][j] != 0 && board.getTiles()[i][j] == board.getTiles()[i+1][j+1] && board.getTiles()[i][j] == board.getTiles()[i+2][j+2]) {
                    if (board.getTiles()[i+3][j+3] == 0) return new Coordinate(i+3, j+3);
                    if (board.getTiles()[i-1][j-1] == 0) return new Coordinate(i-1, j-1);
                }
            }
        }

        //Check if there is 3 in a diagonal
        for (int i = 1; i < 13; i++) {
            for (int j = 3; j < 15; j++) {
                if (board.getTiles()[i][j] != 0 && board.getTiles()[i][j] == board.getTiles()[i+1][j-1] && board.getTiles()[i][j] == board.getTiles()[i+2][j-2]) {
                    if (board.getTiles()[i+3][j-3] == 0) return new Coordinate(i+3, j-3);
                    if (board.getTiles()[i-1][j+1] == 0) return new Coordinate(i-1, j+1);
                }
            }
        }

        //Check if there is 2 in a row
        for (int i = 0; i < 15; i++) {
            for (int j = 1; j < 14; j++) {
                if (board.getTiles()[i][j] != 0 && board.getTiles()[i][j] == board.getTiles()[i][j+1]) {
                    if (board.getTiles()[i][j+2] == 0) return new Coordinate(i, j+2);
                    if (board.getTiles()[i][j-1] == 0) return new Coordinate(i, j-1);
                }
            }
        }

        //Check if there is 2 in a column
        for (int i = 1; i < 14; i++) {
            for (int j = 0; j < 15; j++) {
                if (board.getTiles()[i][j] != 0 && board.getTiles()[i][j] == board.getTiles()[i+1][j]) {
                    if (board.getTiles()[i+2][j] == 0) return new Coordinate(i+2, j);
                    if (board.getTiles()[i-1][j] == 0) return new Coordinate(i-1, j);
                }
            }
        }

        //Check if there is 2 in a diagonal
        for (int i = 1; i < 14; i++) {
            for (int j = 1; j < 14; j++) {
                if (board.getTiles()[i][j] != 0 && board.getTiles()[i][j] == board.getTiles()[i+1][j+1]) {
                    if (board.getTiles()[i+2][j+2] == 0) return new Coordinate(i+2, j+2);
                    if (board.getTiles()[i-1][j-1] == 0) return new Coordinate(i-1, j-1);
                }
            }
        }

        //Check if there is 2 in a diagonal
        for (int i = 1; i < 14; i++) {
            for (int j = 1; j < 14; j++) {
                if (board.getTiles()[i][j] != 0 && board.getTiles()[i][j] == board.getTiles()[i+1][j-1]) {
                    if (board.getTiles()[i+2][j-2] == 0) return new Coordinate(i+2, j-2);
                    if (board.getTiles()[i-1][j+1] == 0) return new Coordinate(i-1, j+1);
                }
            }
        }

        //Check if there is 1
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                if (board.getTiles()[i][j] != 0 ){
                    if (board.getTiles()[i][j+1] == 0) return new Coordinate(i, j+1);
                    if (board.getTiles()[i][j-1] == 0) return new Coordinate(i, j-1);
                    if (board.getTiles()[i+1][j] == 0) return new Coordinate(i+1, j);
                    if (board.getTiles()[i-1][j] == 0) return new Coordinate(i-1, j);
                }
            }
        }

        //If there is none make a random move
        return randomMove(board);
    }
    /**
     * Ellenfél lépése
     * @param board A tábla
     * @return Egy lépés
     */

    public static Coordinate makeMove(Board board){
        Random rand = new Random();
        int r = rand.nextInt(Settings.difficulty);
        if (r == 0) return randomMove(board);
        else 
        try{
        return calculatedMove(board);
        }
        catch(Exception e){
            return randomMove(board);
        }


    }
}
