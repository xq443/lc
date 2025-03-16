package Snowflake;

import java.util.*;

public class FindCakeII {
  // Binary search: Find the first index in `cakes` where value >= x, or return cakes.size() if not found
  public static int binarySearch(List<Integer> cakes, int x) {
    int left = 0, right = cakes.size();
    while (left < right) {
      int mid = (left + right) / 2;
      if (cakes.get(mid) < x) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }

  public static int findNearestCake(int[] a, int x) {
    int n = a.length;
    if (n == 0 || x < 0 || x >= n || a[x] != 1) {
      return -1; // Invalid input case
    }

    // Prefix sum array: p[i] stores the count of empty spaces (0s) in a[0...i-1]
    int[] p = new int[n + 1];
    for (int i = 0; i < n; i++) {
      p[i + 1] = p[i] + (a[i] == 0 ? 1 : 0);
    }

    // Collect all cake positions
    List<Integer> cakes = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (a[i] == 2) {
        cakes.add(i);
      }
    }

    // If no cakes exist, return -1
    if (cakes.isEmpty()) {
      return -1;
    }

    // Find the closest cake using binary search
    int pos = binarySearch(cakes, x);
    int nearestCake = -1;
    int minEmptySpaces = n; // Initialize with a large value

    // Candidate 1: If pos is within bounds, consider cakes.get(pos)
    if (pos < cakes.size()) {
      int cakePos = cakes.get(pos);
      int lo = Math.min(x, cakePos), hi = Math.max(x, cakePos);
      int emptyCount = p[hi] - p[lo + 1];

      if (emptyCount < minEmptySpaces) {
        minEmptySpaces = emptyCount;
        nearestCake = cakePos;
      }
    }

    // Candidate 2: If pos - 1 is valid, consider cakes.get(pos - 1)
    if (pos - 1 >= 0) {
      int cakePos = cakes.get(pos - 1);
      int lo = Math.min(x, cakePos), hi = Math.max(x, cakePos);
      int emptyCount = p[hi] - p[lo + 1];

      if (emptyCount < minEmptySpaces) {
        minEmptySpaces = emptyCount;
        nearestCake = cakePos;
      }
    }

    return nearestCake;
  }

  public static void main(String[] args) {
    int[] a = {0, 1, 0, 2, 0, 2}; // Example array
    int x = 1; // Given person index

    System.out.println("Nearest cake index: " + findNearestCake(a, x)); // Expected output: 3
  }
}
