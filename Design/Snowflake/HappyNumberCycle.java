package Snowflake;

import java.util.*;

public class HappyNumberCycle {
  // Function to compute the next number in the sequence
  private static int getNext(int n) {
    int sum = 0;
    while (n > 0) {
      int digit = n % 10;
      sum += digit * digit;
      n /= 10;
    }
    return sum;
  }

  // Function to detect and print the cycle
  public static void findCycle(int n) {
    Set<Integer> seen = new HashSet<>(); // Track visited numbers
    List<Integer> ret = new ArrayList<>();

    while (n != 1 && !seen.contains(n)) {
      seen.add(n);
      ret.add(n);
      n = getNext(n);
    }
    System.out.println(n); // 58

    if (n == 1) {
      System.out.println("This is a Happy Number.");
    } else {
      System.out.println("Cycle detected: " + ret.subList(ret.indexOf(n), ret.size()));
    }
  }

  public static void main(String[] args) {
    int n = 116; // Example case: 116 should show a cycle
    findCycle(n);
  }
}
