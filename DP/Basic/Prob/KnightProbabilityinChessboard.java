package Basic.Prob;

public class KnightProbabilityinChessboard {
  public double knightProbability(int n, int k, int row, int column) {
    double[][] dp = new double[n][n];
    dp[row][column] = 1.0;

    int[][] dirs = {
        {1,2},{1,-2},{-1,2},{-1,-2},
        {2,1},{-2,1},{2,-1},{-2,-1}
    };


    while(k > 0) {
      double[][] temp = new double[n][n];
      for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
          if(dp[i][j] == 0) continue;
          for(int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x < 0 || x >= n || y < 0 || y >= n) continue;
            temp[x][y] += dp[i][j] / 8.0;
          }
        }
      }
      k--;
      dp = temp;
    }

    double ret = 0.0;
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        ret += dp[i][j];
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    KnightProbabilityinChessboard knight = new KnightProbabilityinChessboard();
    int n = 3, k = 2, row = 0, column = 0;
    System.out.println(knight.knightProbability(n, k, row, column));
  }
}
