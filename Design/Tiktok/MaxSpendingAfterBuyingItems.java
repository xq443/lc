package Tiktok;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaxSpendingAfterBuyingItems {
  public long maxSpending(int[][] values) {
    long ret = 0;
    int n = values.length;
    int m = values[0].length;
    int[] rightMostPosition = new int[n];
    Arrays.fill(rightMostPosition, m - 1);

    for(int day = 1; day <= n * m; day++) {
      int currIdx = -1, currPrice = Integer.MAX_VALUE;
      for(int i = 0; i < n; i++) {
        if(rightMostPosition[i] < 0) continue;
        if(values[i][rightMostPosition[i]] < currPrice) {
          currPrice = values[i][rightMostPosition[i]];
          currIdx = i;
        }
      }
      ret += (long) day * currPrice;
      rightMostPosition[currIdx]--; // row所对应的col位置 --
    }
    return ret;
  }

  public long maxSpending_heap(int[][] values) {
    long ret = 0;
    int n = values.length;
    int m = values[0].length;
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < m; j++) {
        pq.offer(values[i][j]);
      }
    }

    int day = 1;
    while(!pq.isEmpty()) {
      ret += (long) day * pq.poll();
      day++;
    }
    return ret;
  }

  public static void main(String[] args) {
    MaxSpendingAfterBuyingItems m = new MaxSpendingAfterBuyingItems();
    int[][] values = {
        {10, 8, 6, 4, 2},
        {9, 7, 5, 3, 2}
    };
    System.out.println(m.maxSpending(values));

    int[][] values2 = {
        {8, 5, 2},
        {6, 4, 1},
        {9, 7, 3}
    };
    System.out.println(m.maxSpending_heap(values2));
  }
}
