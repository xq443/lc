package Google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MinimizetheMaxEdgeWeight {
  public int minMaxWeight(int n, int[][] edges) {
    if(edges.length < n - 1) return -1;

    // build the adj list
    List<List<int[]>> adj = new ArrayList<>();
    for(int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    for(int[] edge : edges) {
      int src = edge[0], dst = edge[1], weight = edge[2];
      adj.get(dst).add(new int[] {src, weight});
    }

    // dijkstra algo
    int[] minDis = new int[n];
    Arrays.fill(minDis, Integer.MAX_VALUE);
    minDis[0] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0])); // min heap
    pq.offer(new int[] {0, 0}); // max weight, node

    int ret = 0;

    while(!pq.isEmpty()) {
      int[] cur = pq.poll();
      int currWeight = cur[0];
      int currNode = cur[1];
      if(currWeight > minDis[currNode]) continue;
      ret = currWeight;
      n--;

      for(int[] next : adj.get(currNode)) {
        int newNode = next[0];
        int newWeight = next[1];
        if(newWeight < minDis[newNode]) {
          minDis[newNode] = newWeight;
          pq.offer(new int[] {newWeight, newNode});
        }
      }
    }
    return n == 0 ? ret : -1; // check if it's graph
  }

  public static void main(String[] args) {
    MinimizetheMaxEdgeWeight m = new MinimizetheMaxEdgeWeight();
    int n = 5;
    int[][] edges = {
        {1, 0, 1},
        {2, 0, 2},
        {3, 0, 1},
        {4, 3, 1},
        {2, 1, 1}
    };
    System.out.println(m.minMaxWeight(n, edges));
  }
}

// TC: O(logn)
// SC: O(n)