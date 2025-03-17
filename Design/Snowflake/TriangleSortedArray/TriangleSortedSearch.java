package Snowflake.TriangleSortedArray;

public class TriangleSortedSearch {
  // Step 1: Find peak index
  private int findPeak(int[] arr) {
    int left = 0, right = arr.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] < arr[mid + 1]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left; // Peak index
  }

  // Step 2: Binary search in ascending part
  private boolean binarySearchAsc(int[] arr, int left, int right, int target) {
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] == target) return true;
      else if (arr[mid] < target) left = mid + 1;
      else right = mid - 1;
    }
    return false;
  }

  // Step 3: Binary search in descending part
  private boolean binarySearchDesc(int[] arr, int left, int right, int target) {
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] == target) return true;
      else if (arr[mid] > target) left = mid + 1;
      else right = mid - 1;
    }
    return false;
  }

  public boolean searchInTriangleSorted(int[] arr, int target) {
    if (arr.length == 0) return false;

    int peak = findPeak(arr);

    // Search in left (ascending part)
    if (binarySearchAsc(arr, 0, peak, target)) return true;

    // Search in right (descending part)
    return binarySearchDesc(arr, peak + 1, arr.length - 1, target);
  }

  public static void main(String[] args) {
    TriangleSortedSearch t = new TriangleSortedSearch();
    int[] arr = {1, 4, 6, 5, 3};
    System.out.println(t.searchInTriangleSorted(arr, 5)); // true
    System.out.println(t.searchInTriangleSorted(arr, 2)); // false
  }
}
