package Tiktok.Simulation;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> ret = new ArrayList<>();
    int rowStart = 0, colStart = 0;
    int rowEnd = matrix.length -1 , colEnd = matrix[0].length - 1;

    while(rowStart <= rowEnd && colStart <= colEnd) {
      for(int i = colStart; i <= colEnd; i++) {
        ret.add(matrix[rowStart][i]);
      }
      rowStart++;
      for(int i = rowStart; i <= rowEnd; i++) {
        ret.add(matrix[i][colEnd]);
      }
      colEnd--;

      if(rowEnd >= rowStart) {
        for(int i = colEnd; i >= colStart; i--) {
          ret.add(matrix[rowEnd][i]);
        }
        rowEnd--;
      }

      if(colEnd >= colStart) {
        for(int i = rowEnd; i >= rowStart; i--) {
          ret.add(matrix[i][colStart]);
        }
        colStart++;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    SpiralMatrix spiralMatrix = new SpiralMatrix();
    int[][] matrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    System.out.println(spiralMatrix.spiralOrder(matrix));
  }
}
