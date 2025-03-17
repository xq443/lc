package Snowflake.Cake;

import java.util.Arrays;

public class NearestCake {

  // Step 1: Find min distances for each person
  public int minDistances(int[] arr) {
    int n = arr.length;
    int[] dp = new int[n];
    Arrays.fill(dp, Integer.MAX_VALUE); // Initialize to max value

    // Forward Pass: Find nearest `2` from the left
    int nearestCake = -1;
    for (int i = 0; i < n; i++) {
      if (arr[i] == 2) {
        nearestCake = i; // Update nearest cake index
      }
      if (arr[i] == 1 && nearestCake != -1) {
        dp[i] = i - nearestCake; // Distance to left cake
      }
    }

    // Backward Pass: Find nearest `2` from the right
    nearestCake = -1;
    for (int i = n - 1; i >= 0; i--) {
      if (arr[i] == 2) {
        nearestCake = i; // Update nearest cake index
      }
      if (arr[i] == 1 && nearestCake != -1) {
        dp[i] = Math.min(dp[i], nearestCake - i); // Get min distance
      }
    }

    int ret = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      ret = Math.min(ret, dp[i]);
    }
    return ret - 1;
  }

  // Step 2: Find which cake a person eats
  public int getCakeIndex(int[] arr, int personIndex) {
    int n = arr.length;
    if (arr[personIndex] != 1) return -1; // Not a person

    int leftCake = -1, rightCake = -1;
    for (int i = personIndex; i >= 0; i--) {
      if (arr[i] == 2) {
        leftCake = i;
        break;
      }
    }

    for (int i = personIndex; i < n; i++) {
      if (arr[i] == 2) {
        rightCake = i;
        break;
      }
    }

    // Determine the closer cake
    if (leftCake == -1) return rightCake; // Only right available
    if (rightCake == -1) return leftCake; // Only left available
    return (personIndex - leftCake <= rightCake - personIndex) ? leftCake : rightCake;
  }

  public static void main(String[] args) {
    NearestCake n = new NearestCake();
    int[] arr1 = {1, 0, 2};
    System.out.println(n.minDistances(arr1)); // Output: [1, 0, MAX]

    int[] arr2 = {1, 1, 2};
    System.out.println(n.minDistances(arr2)); // Output: [0, 0, MAX]

    int[] arr3 = {1, 0, 0, 2, 1, 0, 1, 0, 2};
    System.out.println(n.minDistances(arr3)); // Output: [3, 2, 1, 0, 1, 1, 1, 1, 0]

    // Follow-up: which cake a person eats
    System.out.println(n.getCakeIndex(arr3, 0)); // Output: 3 (Person at index 0 eats cake at index 3)
    System.out.println(n.getCakeIndex(arr3, 6)); // Output: 8 (Person at index 6 eats cake at index 8)
  }
}
