package Google;

public class GameofLife {
  public void gameOfLife(int[][] board) {
    int m = board.length;
    int n = board[0].length;

    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    // calculate next states
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int live = 0;

        for(int k = 0; k < 8; k++) {
          int new_x = i + dx[k];
          int new_y = j + dy[k];
          if(new_x >= 0 && new_x < m && new_y >= 0 && new_y < n
              && Math.abs(board[new_x][new_y]) == 1) {
            live++;
          }
        }

        // apply rules
        if(board[i][j] == 1 && (live < 2 || live > 3)) {
          board[i][j] = -1;
        }
        if(board[i][j] == 0 && (live == 3)) {
          board[i][j] = 2;
        }
      }
    }

    // finalize the matrix
    for(int i = 0; i < m; i++) {
      for(int j = 0; j < n; j++) {
        if(board[i][j] == -1) {
          board[i][j] = 0;
        } else if(board[i][j] == 2) {
          board[i][j] = 1;
        }
      }
    }
  }

  public static void main(String[] args) {
    GameofLife g = new GameofLife();
    int[][] board = {
        {0, 1, 0},
        {0, 0, 1},
        {1, 1, 1},
        {0, 0, 0}
    };
    g.gameOfLife(board);

    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }
}
