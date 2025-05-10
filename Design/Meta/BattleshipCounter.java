package Meta;

public class BattleshipCounter {
  public int countBattleships(char[][] board) {
    int m = board.length;
    int n = board[0].length;
    boolean[][] visited = new boolean[m][n];
    int count = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'X') {
          if (isValidShip(board, visited, i, j)) {
            count++;
          }
        }
      }
    }
    return count;
  }

  private boolean isValidShip(char[][] board, boolean[][] visited, int i, int j) {
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


  private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public int countBattleships_dfs(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }

    int count = 0;
    int rows = grid.length;
    int cols = grid[0].length;
    boolean[][] visited = new boolean[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 'X' && !visited[i][j]) {
          // Explore in all four directions
          for (int[] dir : DIRECTIONS) {
            int length = dfs(grid, visited, i, j, dir[0], dir[1]);
            if (length == 3) {
              count++;
              break; // Only count once per ship
            }
          }
        }
      }
    }

    return count;
  }

  private int dfs(char[][] grid, boolean[][] visited, int i, int j, int di, int dj) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length ||
        grid[i][j] != 'X' || visited[i][j]) {
      return 0;
    }

    visited[i][j] = true;
    int length = 1;

    // Continue in the given direction
    length += dfs(grid, visited, i + di, j + dj, di, dj);

    return length;
  }

  public static void main(String[] args) {
    BattleshipCounter b = new BattleshipCounter();
    char[][] board = {
        {'X', '.', 'X', '.'},
        {'X', 'X', 'X', '.'},
        {'X', '.', 'X', '.'}
    };
    int count = b.countBattleships(board);
    //int count2 = b.countBattleships_dfs(board);
    System.out.println("Number of ships of length 3: " + count);
  }
}
