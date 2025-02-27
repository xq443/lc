package Snowflake.wordsearch;

public class WordSearch {
  public boolean exist(char[][] board, String word) {
    if(board == null || word == null || board.length == 0 || board[0].length == 0) return false;
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[0].length; j++) {
        if(board[i][j] == word.charAt(0)) {
          if (dfs(board, word, i, j, 0)) return true;
        }
      }
    }
    return false;
  }

  public boolean dfs(char[][] board, String word, int i, int j, int index) {
    if(index == word.length()) return true;
    int m = board.length;
    int n = board[0].length;
    if(i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(index)) return false;

    char temp = board[i][j];
    board[i][j] = '!';

    if(dfs(board, word, i + 1, j, index + 1) ||
    dfs(board, word, i - 1, j, index + 1) ||
    dfs(board, word, i, j + 1, index + 1) ||
    dfs(board, word, i, j - 1, index + 1)) return true;

    board[i][j] = temp;
    return false;
  }
  // Time Complexity: O(mn 4^l)
  // Space Complexity: O(L) (due to recursion depth)
}
