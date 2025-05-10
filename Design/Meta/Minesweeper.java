package Meta;

import java.util.*;

public class Minesweeper {
  public static int[][] generateBoard(int xlen, int ylen, int mineNum) {
    int[][] board = new int[ylen][xlen];
    Set<Integer> minePositions = new HashSet<>();
    Random random = new Random();

    while (minePositions.size() < mineNum) {
      int position = random.nextInt(xlen * ylen); // Unique index in flattened grid
      minePositions.add(position);
    }

    for (int pos : minePositions) {
      int x = pos % xlen;
      int y = pos / xlen;
      board[y][x] = -1;
    }

    return board;
  }

  public static void main(String[] args) {
    int[][] board = generateBoard(5, 5, 5); // Example: 5x5 grid with 5 mines

    for (int[] row : board) {
      for (int cell : row) {
        System.out.print(cell + " ");
      }
      System.out.println();
    }
  }
}
