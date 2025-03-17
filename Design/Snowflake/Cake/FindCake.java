package Snowflake.Cake;

import java.util.*;

public class FindCake {
  public int findMinEmptySpaces(int[] a) {
    int n = a.length;
    if (n == 0) return -1;

    // Prefix sum array: p[i] stores the count of empty spaces (0s) in a[0...i-1]
    int[] p = new int[n + 1];
    for (int i = 0; i < n; i++) {
      p[i + 1] = p[i] + (a[i] == 0 ? 1 : 0);
    }

    // Lists to store positions of people (1s) and cakes (2s)
    List<Integer> people = new ArrayList<>();
    List<Integer> cakes = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (a[i] == 1) {
        people.add(i);
      } else if (a[i] == 2) {
        cakes.add(i);
      }
    }

    // If there are no people or no cakes, return -1 (undefined case)
    if (people.isEmpty() || cakes.isEmpty()) {
      return -1;
    }

    // Two-pointer approach to find the minimum empty spaces between any person and cake
    int i = 0, j = 0, minEmptySpaces = n;
    while (i < people.size() && j < cakes.size()) {
      int personPos = people.get(i);
      int cakePos = cakes.get(j);

      // Find the range between person and cake
      int lo = Math.min(personPos, cakePos);
      int hi = Math.max(personPos, cakePos);

      // Count empty spaces in range (lo, hi)
      int emptyCount = p[hi] - p[lo + 1];
      minEmptySpaces = Math.min(minEmptySpaces, emptyCount);

      // Move the pointer that is behind to minimize the difference
      if (personPos < cakePos) {
        i++;
      } else {
        j++;
      }
    }

    return minEmptySpaces;
  }

  public static void main(String[] args) {
    FindCake fc = new FindCake();

    int[] a1 = {1, 0, 2}; // Expected output: 1
    System.out.println("Min empty spaces: " + fc.findMinEmptySpaces(a1));

    int[] a2 = {1, 1, 2}; // Expected output: 0
    System.out.println("Min empty spaces: " + fc.findMinEmptySpaces(a2));

    int[] a3 = {1, 0, 0, 2, 0, 1, 2}; // Additional test case
    System.out.println("Min empty spaces: " + fc.findMinEmptySpaces(a3));
  }
}
