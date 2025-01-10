package Apple;

import java.util.Arrays;

public class FindGridRegionAverageSolved {
  public int[][] resultGrid(int[][] image, int threshold) {
    int n = image.length;
    int m = image[0].length;
    int[][] sum = new int[n][m];
    int[][] count = new int[n][m];
    for (int i = 0; i < n - 2; i++) {
      for (int j = 0; j < m - 2; j++) {
        if(isValidRegion(image, i, j, threshold)) {
          int subGridSum = subSum(image, i, j);
          for(int k = i; k < i + 3; k++) {
            for(int l = j; l < j + 3; l++) {
              sum[k][l] += subGridSum / 9;
              count[k][l]++;
            }
          }
        }
      }
    }

    // check overlapping
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < m; j++) {
        if(count[i][j] > 0) {
          image[i][j] = sum[i][j] / count[i][j];
        }
      }
    }
    return image;
  }

  private boolean isValidRegion(int[][] image, int i, int j, int threshold) {
    for(int k = i; k < i + 3; k++) {
      for(int l = j; l < j + 3; l++) {
        if (k > i && Math.abs(image[k][l] - image[k - 1][l]) > threshold)
          return false;
        if (l > j && Math.abs(image[k][l] - image[k][l - 1]) > threshold)
          return false;
      }
    }
    return true;
  }

  private int subSum(int[][] image, int i, int j) {
    int sum = 0;
    for(int k = i; k < i + 3; k++) {
      for(int l = j; l < j + 3; l++) {
        sum += image[k][l];
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    FindGridRegionAverageSolved f = new FindGridRegionAverageSolved();
    int[][] image = {{5,6,7,10},{8,9,10,10},{11,12,13,10}};
    int threshold = 3;
    System.out.println(Arrays.deepToString(f.resultGrid(image, threshold)));
  }
}
