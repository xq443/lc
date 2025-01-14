package Google;

public class SetMatrixZeroes {
  public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    boolean flagFirstRowZero = false;
    boolean flagFirstColZero = false;

    // check out the first row and col
    for (int[] ints : matrix) {
      if (ints[0] == 0) {
        flagFirstRowZero = true;
        break;
      }
    }

    for(int j = 0; j < n; j++) {
      if(matrix[0][j] == 0) {
        flagFirstColZero = true;
        break;
      }
    }

    // check out the inner part, mark the first row and col
    for(int i = 1; i < m; i++) {
      for(int j = 1; j < n; j++) {
        if(matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }

    // zero out the inner part based on the first row and col
    for(int i = 1; i < m; i++) {
      for(int j = 1; j < n; j++) {
        if(matrix[i][0] == 0 || matrix[0][j] == 0) {
          matrix[i][j] = 0;
        }
      }
    }

    // zero out first row and col
    if(flagFirstRowZero) {
      for(int i = 0; i < m; i++) {
        matrix[i][0] = 0;
      }
    }

    if(flagFirstColZero) {
      for(int i = 0; i < n; i++) {
        matrix[0][i] = 0;
      }
    }
  }

  public static void main(String[] args) {
    SetMatrixZeroes s = new SetMatrixZeroes();

    int[][] matrix = {
        {1, 0, 1},
        {1, 1, 0},
        {1, 1, 1}
    };

    s.setZeroes(matrix);
    for (int[] ints : matrix) {
      for (int j = 0; j < matrix[0].length; j++) {
        System.out.print(ints[j] + " ");
      }
      System.out.println();
    }
  }
}
