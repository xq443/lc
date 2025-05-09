package Meta;

public class BattleshipCounter {
  public static int countBattleships(char[][] board) {
    int m = board.length;
    int n = board[0].length;
    boolean[][] visited = new boolean[m][n];
    int count = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'X' && !visited[i][j]) {
          if (isValidShip(board, visited, i, j)) {
            count++;
          }
        }
      }
    }

    return count;
  }

  private static boolean isValidShip(char[][] board, boolean[][] visited, int i, int j) {
    int m = board.length, n = board[0].length;

    // Check horizontal ship of length 3
    if (j + 2 < n &&
        board[i][j] == 'X' && board[i][j+1] == 'X' && board[i][j+2] == 'X' &&
        (j + 3 >= n || board[i][j+3] != 'X') && (j - 1 < 0 || board[i][j-1] != 'X')) {
      visited[i][j] = visited[i][j+1] = visited[i][j+2] = true;
      return true;
    }

    // Check vertical ship of length 3
    if (i + 2 < m &&
        board[i][j] == 'X' && board[i+1][j] == 'X' && board[i+2][j] == 'X' &&
        (i + 3 >= m || board[i+3][j] != 'X') && (i - 1 < 0 || board[i-1][j] != 'X')) {
      visited[i][j] = visited[i+1][j] = visited[i+2][j] = true;
      return true;
    }

    return false;
  }

  public static void main(String[] args) {
    char[][] board = {
        {'X', '.', '.', 'X'},
        {'X', 'X', 'X', 'X'},
        {'X', '.', '.', 'X'}
    };
    int count = countBattleships(board);
    System.out.println("Number of ships of length 3: " + count);
  }
}
