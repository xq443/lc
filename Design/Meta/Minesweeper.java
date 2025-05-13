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

  public static void fillMineCounts(int[][] board) {
    int ylen = board.length;
    int xlen = board[0].length;

    int[] dx = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};

    for(int y = 0; y < ylen; y++) {
      for(int x = 0; x < xlen; x++) {
        if(board[y][x] == -1) continue;
        int mine = 0;
        for(int i = 0; i < 8; i++) {
          int nx = x + dx[i];
          int ny = y + dy[i];
          if(nx >= 0 && nx < xlen && ny >= 0 && ny < ylen && board[ny][nx] == -1) {
            mine++;
          }
        }
        board[y][x] = mine;
      }
    }
  }

  public static void main(String[] args) {
    int[][] board = generateBoard(5, 5, 5); // Example: 5x5 grid with 5 mines

    for (int[] row : board) {
      for (int cell : row) {
        System.out.print(cell + " ");
      }
      System.out.println();
    }

    System.out.println("the number of mines are: ");


    fillMineCounts(board);

    for (int[] row : board) {
      for (int cell : row) {
        if (cell == -1) {
          System.out.print("* ");
        } else {
          System.out.print(cell + " ");
        }
      }
      System.out.println();
    }
  }
}
