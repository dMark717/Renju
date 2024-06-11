import java.util.LinkedList;

/**
 * Board
 * Ez az osztály felelős a tábla kezeléséért
 */
public class Board {
  // Current player turn
  private int playerTurn = 1;
  // Board tiles
  private int[][] tiles = new int[15][15];
  // History with linked list
  private LinkedList<Coordinate> history = new LinkedList<>();


  /**
   * Konstruktor
   */
  public Board() {
    // Initialize the board
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        tiles[i][j] = 0;
      }
    }
  }

  //Setterek

  /**
   * Beállítja a játékos sorszámát
   * @param playerTurn A játékos sorszáma
   */
  public void setPlayerTurn(int playerTurn) {
    this.playerTurn = playerTurn;
  }

  /**
   * Beállítja a tábla mezőit
   * @param tiles A tábla mezői
   */
  public void setTiles(int[][] tiles) {
    this.tiles = tiles;
  }

  /**
   * Beállítja a történelmet
   */
  public void setTile(int x, int y, int player) {
    this.tiles[x][y] = player;
  }

  /**
   * Beállítja a történelmet
   * @param history A történelem
   */
  public void setHistory(LinkedList<Coordinate> history) {
    this.history = history;
  }

  //Getterek

  /**
   * Visszaadja a játékos sorszámát
   * @return A játékos sorszáma
   */
  public int getPlayerTurn() {
    return playerTurn;
  }

  /**
   * Visszaadja a tábla mezőit
   * @return A tábla mezői
   */
  public int[][] getTiles() {
    return tiles;
  }

  /**
   * Visszaadja a történelmet
   * @return A történelem
   */
  public LinkedList<Coordinate> getHistory() {
    return history;
  }

  // Deep copy the tiles

  /**
   * Visszaadja a tábla másolatát
   * @param original A tábla
   * @return A tábla másolata
   */
  public int[][] copyTiles(int[][] original) {
    int[][] copy = new int[original.length][original[0].length];
    for (int i = 0; i < original.length; i++) {
      for (int j = 0; j < original[i].length; j++) {
        copy[i][j] = original[i][j];
      }
    }
    return copy;
  }

  // Make a move

  /**
   * Létrehoz egy lépést
   * @param x A lépés x koordinátája
   * @param y A lépés y koordinátája
   * @param player A játékos sorszáma
   */
  public void makeMove(int x, int y, int player) {
    // Add the move to the history
    history.addLast(new Coordinate(x, y));

    tiles[x][y] = player;
    if (player == 1) {
      playerTurn = 2;
    } else {
      playerTurn = 1;
    }
  }
  // Undo a move

  /**
   * Visszavon egy lépést
   */
  public void undoMove() {
    if (history.size() > 0) {
      Coordinate lastMove = history.removeLast();
      tiles[lastMove.x][lastMove.y] = 0;
      if (playerTurn == 1) {
        playerTurn = 2;
      } else {
        playerTurn = 1;
      }
    }
  }

  // Check if the move is legal

  /**
   * Megvizsgálja, hogy a lépés szabályos-e
   * @param x A lépés x koordinátája
   * @param y A lépés y koordinátája
   * @param player A játékos sorszáma
   * @return Igaz, ha a lépés szabályos, hamis, ha nem
   */
  public boolean moveIsLegal(int x, int y, int player) {
    if (tiles[x][y] != 0)
      return false;
    // Additional rules for dark for renju
    if (player == 1 && Settings.gamemodeIsRenju) {
      try{
      tiles[x][y] = 1;
      if (checkSix()) {
        tiles[x][y] = 0;
        return false;
      }
      if (checkDoubleThree(x, y)) {
        tiles[x][y] = 0;
        return false;
      }
      if (checkDoubleFour(x, y)) {
        tiles[x][y] = 0;
        return false;
      }
    }
    catch(Exception e){
      return true;
    }
    }

    return true;
  }

  // Check if the game is over

  /**
   * Megvizsgálja, hogy a játék véget ért-e
   * @return A játékos sorszáma, ha véget ért, 0, ha nem
   */
  public int checkWin() {
    // Check horizontal
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 11; j++) {
        if (tiles[i][j] != 0 && tiles[i][j] == tiles[i][j + 1]
            && tiles[i][j] == tiles[i][j + 2] && tiles[i][j] == tiles[i][j + 3]
            && tiles[i][j] == tiles[i][j + 4]) {
          return tiles[i][j];
        }
      }
    }

    // Check vertical
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 15; j++) {
        if (tiles[i][j] != 0 && tiles[i][j] == tiles[i + 1][j]
            && tiles[i][j] == tiles[i + 2][j] && tiles[i][j] == tiles[i + 3][j]
            && tiles[i][j] == tiles[i + 4][j]) {
          return tiles[i][j];
        }
      }
    }

    // Check diagonal
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 11; j++) {
        if (tiles[i][j] != 0 && tiles[i][j] == tiles[i + 1][j + 1]
            && tiles[i][j] == tiles[i + 2][j + 2]
            && tiles[i][j] == tiles[i + 3][j + 3]
            && tiles[i][j] == tiles[i + 4][j + 4]) {
          return tiles[i][j];
        }
      }
    }

    // Check diagonal
    for (int i = 0; i < 11; i++) {
      for (int j = 4; j < 15; j++) {
        if (tiles[i][j] != 0 && tiles[i][j] == tiles[i + 1][j - 1]
            && tiles[i][j] == tiles[i + 2][j - 2]
            && tiles[i][j] == tiles[i + 3][j - 3]
            && tiles[i][j] == tiles[i + 4][j - 4]) {
          return tiles[i][j];
        }
      }
    }
    return 0;
  }

  
  /** 
   * @return boolean
   */
  // Check if there is 6 in a row
  public boolean checkSix() {
    // Check horizontal
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 10; j++) {
        if (tiles[i][j] != 0 && tiles[i][j] == tiles[i][j + 1]
            && tiles[i][j] == tiles[i][j + 2] && tiles[i][j] == tiles[i][j + 3]
            && tiles[i][j] == tiles[i][j + 4]
            && tiles[i][j] == tiles[i][j + 5]) {
          return true;
        }
      }
    }

    // Check vertical
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 15; j++) {
        if (tiles[i][j] != 0 && tiles[i][j] == tiles[i + 1][j]
            && tiles[i][j] == tiles[i + 2][j] && tiles[i][j] == tiles[i + 3][j]
            && tiles[i][j] == tiles[i + 4][j]
            && tiles[i][j] == tiles[i + 5][j]) {
          return true;
        }
      }
    }

    // Check diagonal
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (tiles[i][j] != 0 && tiles[i][j] == tiles[i + 1][j + 1]
            && tiles[i][j] == tiles[i + 2][j + 2]
            && tiles[i][j] == tiles[i + 3][j + 3]
            && tiles[i][j] == tiles[i + 4][j + 4]
            && tiles[i][j] == tiles[i + 5][j + 5]) {
          return true;
        }
      }
    }

    // Check diagonal
    for (int i = 0; i < 10; i++) {
      for (int j = 5; j < 15; j++) {
        if (tiles[i][j] != 0 && tiles[i][j] == tiles[i + 1][j - 1]
            && tiles[i][j] == tiles[i + 2][j - 2]
            && tiles[i][j] == tiles[i + 3][j - 3]
            && tiles[i][j] == tiles[i + 4][j - 4]
            && tiles[i][j] == tiles[i + 5][j - 5]) {
          return true;
        }
      }
    }

    return false;
  }

  // Check for a double 3 intersection
  public boolean checkDoubleThree(int x, int y) {
    int vertical = 0;
    int horizontal = 0;
    int diagonal1 = 0;
    int diagonal2 = 0;

    // Check Horizontally
    if (tiles[x + 1][y] == 1 && tiles[x + 2][y] == 1
        && tiles[x + 3][y] == 0 && tiles[x - 1][y] == 0)
      horizontal = 1;
    else if (tiles[x - 1][y] == 1 && tiles[x - 2][y] == 1
        && tiles[x - 3][y] == 0 && tiles[x + 1][y] == 0)
      horizontal = 1;
    else if (tiles[x - 1][y] == 1 && tiles[x + 1][y] == 1
        && tiles[x + 2][y] == 0 && tiles[x - 2][y] == 0)
      horizontal = 1;

    // Check Vertically
    if (tiles[x][y + 1] == 1 && tiles[x][y + 2] == 1
        && tiles[x][y + 3] == 0 && tiles[x][y - 1] == 0)
      vertical = 1;
    else if (tiles[x][y - 1] == 1 && tiles[x][y - 2] == 1
        && tiles[x][y - 3] == 0 && tiles[x][y + 1] == 0)
      vertical = 1;
    else if (tiles[x][y - 1] == 1 && tiles[x][y + 1] == 1
        && tiles[x][y + 2] == 0 && tiles[x][y - 2] == 0)
      vertical = 1;

    // Check Diagonally
    if (tiles[x + 1][y + 1] == 1 && tiles[x + 2][y + 2] == 1
        && tiles[x + 3][y + 3] == 0 && tiles[x - 1][y - 1] == 0)
      diagonal1 = 1;
    else if (tiles[x - 1][y - 1] == 1 && tiles[x - 2][y - 2] == 1
        && tiles[x - 3][y - 3] == 0 && tiles[x + 1][y + 1] == 0)
      diagonal1 = 1;
    else if (tiles[x - 1][y - 1] == 1 && tiles[x + 1][y + 1] == 1
        && tiles[x + 2][y + 2] == 0 && tiles[x - 2][y - 2] == 0)
      diagonal1 = 1;

    // Check Diagonally
    if (tiles[x + 1][y - 1] == 1 && tiles[x + 2][y - 2] == 1
        && tiles[x + 3][y - 3] == 0 && tiles[x - 1][y + 1] == 0)
      diagonal2 = 1;
    else if (tiles[x - 1][y + 1] == 1 && tiles[x - 2][y + 2] == 1
        && tiles[x - 3][y + 3] == 0 && tiles[x + 1][y - 1] == 0)
      diagonal2 = 1;
    else if (tiles[x - 1][y + 1] == 1 && tiles[x + 1][y - 1] == 1
        && tiles[x + 2][y - 2] == 0 && tiles[x - 2][y + 2] == 0)
      diagonal2 = 1;

    // Check the requirement
    if (vertical + horizontal + diagonal1 + diagonal2 >= 2)
      return true;
    else
      return false;
  }

  // Check for a double 4 intersection
  public boolean checkDoubleFour(int x, int y) {
    int vertical = 0;
    int horizontal = 0;
    int diagonal1 = 0;
    int diagonal2 = 0;

    // Check Horizontally
    if (tiles[x + 1][y] == 1 && tiles[x + 2][y] == 1
        && tiles[x + 3][y] == 1)
      horizontal = 1;
    else if (tiles[x - 1][y] == 1 && tiles[x - 2][y] == 1
        && tiles[x - 3][y] == 1)
      horizontal = 1;
    else if (tiles[x - 1][y] == 1 && tiles[x + 1][y] == 1
        && tiles[x + 2][y] == 1)
      horizontal = 1;
    else if (tiles[x - 1][y] == 1 && tiles[x - 2][y] == 1
        && tiles[x + 1][y] == 1)
      horizontal = 1;

    // Check Vertically
    if (tiles[x][y + 1] == 1 && tiles[x][y + 2] == 1
        && tiles[x][y + 3] == 1)
      vertical = 1;
    else if (tiles[x][y - 1] == 1 && tiles[x][y - 2] == 1
        && tiles[x][y - 3] == 1)
      vertical = 1;
    else if (tiles[x][y - 1] == 1 && tiles[x][y + 1] == 1
        && tiles[x][y + 2] == 1)
      vertical = 1;
    else if (tiles[x][y - 1] == 1 && tiles[x][y - 2] == 1
        && tiles[x][y + 1] == 1)
      vertical = 1;

    // Check Diagonally
    if (tiles[x + 1][y + 1] == 1 && tiles[x + 2][y + 2] == 1
        && tiles[x + 3][y + 3] == 1)
      diagonal1 = 1;
    else if (tiles[x - 1][y - 1] == 1 && tiles[x - 2][y - 2] == 1
        && tiles[x - 3][y - 3] == 1)
      diagonal1 = 1;
    else if (tiles[x - 1][y - 1] == 1 && tiles[x + 1][y + 1] == 1
        && tiles[x + 2][y + 2] == 1)
      diagonal1 = 1;
    else if (tiles[x - 1][y - 1] == 1 && tiles[x - 2][y - 2] == 1
        && tiles[x + 1][y + 1] == 1)
      diagonal1 = 1;

    // Check Diagonally
    if (tiles[x + 1][y - 1] == 1 && tiles[x + 2][y - 2] == 1
        && tiles[x + 3][y - 3] == 1)
      diagonal2 = 1;
    else if (tiles[x - 1][y + 1] == 1 && tiles[x - 2][y + 2] == 1
        && tiles[x - 3][y + 3] == 1)
      diagonal2 = 1;
    else if (tiles[x - 1][y + 1] == 1 && tiles[x + 1][y - 1] == 1
        && tiles[x + 2][y - 2] == 1)
      diagonal2 = 1;
    else if (tiles[x - 1][y + 1] == 1 && tiles[x - 2][y + 2] == 1
        && tiles[x + 1][y - 1] == 1)
      diagonal2 = 1;

    // Check the requirement
    if (vertical + horizontal + diagonal1 + diagonal2 >= 2)
      return true;
    else
      return false;
  }
}