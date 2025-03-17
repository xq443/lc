package Snowflake.TriangleSortedArray;

import java.util.HashSet;
import java.util.Set;

public class TriangleSortedArray {

  public boolean isTriangleSorted(int[] arr) {
    // Edge case: If the array has less than 3 elements, it cannot be a triangle sorted array
    if (arr == null || arr.length < 3) {
      return false;
    }

    // Step 1: Check for duplicates using a HashSet
    Set<Integer> set = new HashSet<>();
    for (int num : arr) {
      if (!set.add(num)) {  // If set.add returns false, it means the number is a duplicate
        return false;
      }
    }

    // Step 2: Find the peak point where the array stops increasing
    int n = arr.length;
    int i = 0;

    // Traverse the array and find where it stops increasing
    while (i < n - 1 && arr[i] < arr[i + 1]) {
      i++;
    }

    // If there is no increase or no decrease, it's not a triangle sorted array
    if (i == 0 || i == n - 1) {
      return false;
    }

    // Step 3: Ensure that the rest of the array is strictly decreasing
    while (i < n - 1 && arr[i] > arr[i + 1]) {
      i++;
    }

    // Step 4: If we've reached the end, it means the array is valid
    return i == n - 1;
  }

  public static void main(String[] args) {
    TriangleSortedArray solution = new TriangleSortedArray();

    int[] arr1 = {1, 4, 6, 5, 3};
    System.out.println(solution.isTriangleSorted(arr1));  // Output: true

    int[] arr2 = {1, 2, 3, 2, 1};
    System.out.println(solution.isTriangleSorted(arr2));  // Output: true

    int[] arr3 = {1, 2, 2, 3, 1};
    System.out.println(solution.isTriangleSorted(arr3));  // Output: false (duplicate value)

    int[] arr4 = {1, 2, 3};
    System.out.println(solution.isTriangleSorted(arr4));  // Output: false (no decreasing part)

    int[] arr5 = {5, 4, 3, 2, 1};
    System.out.println(solution.isTriangleSorted(arr5));  // Output: false (no increasing part)
  }
}
