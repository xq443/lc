package Meta;

import java.util.*;

public class BattleShipPosition {
  public int countBattleships(char[][] board) {
    int m = board.length;
    int n = board[0].length;
    boolean[][] visited = new boolean[m][n];
    int count = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'X' && !visited[i][j]) {
          List<int[]> shipPositions = getShipPositions(board, visited, i, j);
          if (!shipPositions.isEmpty()) {
            count++;
            System.out.print("Ship " + count + " positions: ");
            for (int[] pos : shipPositions) {
              System.out.print(Arrays.toString(pos) + " ");
            }
            System.out.println();
          }
        }
      }
    }
    return count;
  }

  private List<int[]> getShipPositions(char[][] board, boolean[][] visited, int i, int j) {
    int m = board.length, n = board[0].length;
    List<int[]> positions = new ArrayList<>();

    // Check horizontal ship of length 3
    if (j + 2 < n &&
        board[i][j] == 'X' && board[i][j+1] == 'X' && board[i][j+2] == 'X' &&
        (j + 3 >= n || board[i][j+3] != 'X') &&
        (j - 1 < 0 || board[i][j-1] != 'X') &&
        !visited[i][j] && !visited[i][j+1] && !visited[i][j+2]) {

      visited[i][j] = visited[i][j+1] = visited[i][j+2] = true;
      positions.add(new int[]{i, j});
      positions.add(new int[]{i, j+1});
      positions.add(new int[]{i, j+2});
      return positions;
    }

    // Check vertical ship of length 3
    if (i + 2 < m &&
        board[i][j] == 'X' && board[i+1][j] == 'X' && board[i+2][j] == 'X' &&
        (i + 3 >= m || board[i+3][j] != 'X') &&
        (i - 1 < 0 || board[i-1][j] != 'X') &&
        !visited[i][j] && !visited[i+1][j] && !visited[i+2][j]) {

      visited[i][j] = visited[i+1][j] = visited[i+2][j] = true;
      positions.add(new int[]{i, j});
      positions.add(new int[]{i+1, j});
      positions.add(new int[]{i+2, j});
      return positions;
    }

    return positions; // Empty if not a valid ship
  }

  public static void main(String[] args) {
    char[][] board = {
        {'X', '.', '.', 'X'},
        {'X', '.', '.', 'X'},
        {'X', '.', '.', 'X'},
        {'.', '.', '.', '.'}
    };

    BattleShipPosition counter = new BattleShipPosition();
    int total = counter.countBattleships(board);
    System.out.println("Total ships: " + total);
  }
}
