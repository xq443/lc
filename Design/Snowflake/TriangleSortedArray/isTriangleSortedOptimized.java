package Snowflake.TriangleSortedArray;

public class isTriangleSortedOptimized {
  public boolean isTriangleSortedOptimized(int[] arr) {
    if (arr.length < 3)
      return false;

    int n = arr.length;
    int peak = 0;

    // Detect peak & check increasing order
    for (int i = 1; i < n; i++) {
      if (arr[i] == arr[i - 1])
        return false; // Check duplicates
      if (arr[i] > arr[i - 1]) {
        peak = i;
      } else {
        break;
      }
    }

    // Peak should not be at the first or last index
    if (peak == 0 || peak == n - 1)
      return false;

    // Check strictly decreasing after peak
    for (int i = peak + 1; i < n; i++) {
      if (arr[i] >= arr[i - 1])
        return false;
    }

    // check duplication
    int left = 0, right = n - 1;
    while (left < right) {
      if(arr[left] == arr[right]) return false;
      left++;
      right--;
    }

    return true;
  }

  public static void main(String[] args) {
    isTriangleSortedOptimized is = new isTriangleSortedOptimized();
    int[] arr1 = {1, 4, 6, 5, 3};
    System.out.println(is.isTriangleSortedOptimized(arr1));  // Output: true

    int[] arr2 = {1, 2, 3, 2, 1};
    System.out.println(is.isTriangleSortedOptimized(arr2));  // Output: false

    int[] arr3 = {1, 2, 2, 3, 1};
    System.out.println(is.isTriangleSortedOptimized(arr3));  // Output: false (duplicate value)

  }
}
