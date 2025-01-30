package Apple;

public class ValidSudoku {
  public boolean isValidSudoku(char[][] board) {
    int n = board.length;
    int m = board[0].length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] != '.') {
          char num = board[i][j];
          if(!safe(board, i, j, num)) return false;
        }
      }
    }
    return true;
  }

  public boolean safe(char[][] board, int row, int col, char num) {
    for(int k = 0; k < 9; k++) {
      if((k != col && board[row][k] == num) || (k != row && board[k][col] == num)) return false;
    }

    int startRow = 3 * (row / 3);
    int startCol = 3 * (col / 3);
    for(int i = startRow; i < startRow + 3; i++) {
      for(int j = startCol; j < startCol + 3; j++) {
        if(i != row && j != col && board[i][j] == num) return false;
      }
    }
    return true;
  }
}
