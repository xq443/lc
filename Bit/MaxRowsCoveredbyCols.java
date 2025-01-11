import java.util.ArrayList;
import java.util.List;

public class MaxRowsCoveredbyCols {
  public int maximumRows(int[][] matrix, int numSelect) {
    int n = matrix.length;
    int m = matrix[0].length;

    // concatenate the row as binary representation
    List<Integer> rows = new ArrayList<>();
    for(int i = 0; i < n; i++) {
      int num = 0;
      for(int j = 0; j < m; j++) {
        num = num * 2 + matrix[i][j];
      }
      rows.add(num);
    }

    int ret = 0;

    // enumerate all possible states
    for(int state = 0; state < (1 << m); state++) {
      if(countOnes(state) != numSelect) continue;
      int cnt = 0;
      for(int row : rows) {
        if((state & row) == row) {
          cnt++;
        }
        ret = Math.max(ret, cnt);
      }
    }
    return ret;
  }

  public int countOnes(int state) {
    int count = 0;
    while(state != 0) {
      state = state & (state - 1);
      count++;
    }
    return count;
  }

  public static void main(String[] args) {
    MaxRowsCoveredbyCols m =  new MaxRowsCoveredbyCols();
    int[][] matrix = {{0,0,0},{1,0,1},{0,1,1},{0,0,1}};
    int numSelect = 2;
    System.out.println(m.maximumRows(matrix, numSelect));
  }
}
