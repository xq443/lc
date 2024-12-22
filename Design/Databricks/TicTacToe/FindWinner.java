package Databricks.TicTacToe;

public class FindWinner {
  public String tictactoe(int[][] moves) {
    char[][] grid = new char[moves.length][moves.length];

    // fill the grid based on the moves
    for(int i = 0; i < moves.length; i++) {
      int row = moves[i][0];
      int col = moves[i][1];
      grid[row][col] = (i % 2 == 0 ? 'X' : 'O');
    }

    // check the winner
    for(int i = 0; i < moves.length; i++) {
      if(grid[i][0] != '\0' && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
        return grid[i][0] == 'X' ? "A" : "B";
      }

      if(grid[0][i] != '\0' && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
        return grid[0][i] == 'X' ? "A" : "B";
      }
    }

    // check diagonal
    if (grid[0][0] != '\0' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
      return grid[0][0] == 'X' ? "A" : "B";
    }
    if (grid[0][2] != '\0' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
      return grid[0][2] == 'X' ? "A" : "B";
    }

    return moves.length == 9 ? "Drawing" : "Pending";
  }

  public static void main(String[] args) {
    FindWinner winner = new FindWinner();
    int[][] moves = {{0,0},{2,0},{1,1},{2,1},{2,2}};
    System.out.println(winner.tictactoe(moves));
  }
}
