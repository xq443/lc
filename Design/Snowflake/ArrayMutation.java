package Snowflake;

public class ArrayMutation {
  public static int[] mutateArray(int[] arr, int k) {
    int n = arr.length;
    int[] result = new int[n - k + 1];

    // Process windows from right to left
    for (int i = n - k; i >= 0; i--) {
      boolean isDecreasing = true;
      int minValue = arr[i];

      // Check if the subarray [i, i+k-1] is strictly decreasing
      for (int j = i; j < i + k - 1; j++) {
        if (arr[j] <= arr[j + 1]) {
          isDecreasing = false;
          break;
        }
        minValue = Math.min(minValue, arr[j + 1]);
      }

      result[i] = isDecreasing ? minValue : -1;
    }

    return result;
  }

  public static void main(String[] args) {
    int[] arr = {1, 9, 7, 7, 6, 5, 4, 3, 2, 1};
    int k = 3;
    int[] result = mutateArray(arr, k);

    // Print result
    for (int value : result) {
      System.out.print(value + " ");
    }
  }
}
