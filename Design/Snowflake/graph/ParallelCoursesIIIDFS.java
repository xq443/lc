package Snowflake.graph;

import java.util.ArrayList;
import java.util.List;

public class ParallelCoursesIIIDFS {
  public int minimumTime(int n, int[][] relations, int[] time) {
    List<List<Integer>> adj = new ArrayList<>();
    for(int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    boolean[] visited = new boolean[n + 1];
    int[] memo = new int[n + 1];

    for(int[] relation : relations) {
      int prep = relation[0];
      int course = relation[1];
      adj.get(prep).add(course);
    }

    int ret = 0;
    for(int i = 1; i <= n; i++) {
      ret = Math.max(ret, dfs(i, adj, time, memo, visited));
    }
    return ret;
  }

  public int dfs(int i, List<List<Integer>> adj, int[] time, int[] memo, boolean[] visited) {
    if(visited[i]) return memo[i];
    // base case
    int max = time[i - 1]; // Minimum time required is its own duration

    for(int next : adj.get(i)) {
      max = Math.max(max, time[i - 1] + dfs(next, adj, time, memo, visited));
    }
    visited[i] = true;
    memo[i] = max;
    return max;
  }

  /**
   * In the worst case, we traverse every node (O(V)) and visit all edges (O(E)) once.
   * Since we use memoization, each course is only computed once.
   * The recursion explores all dependencies efficiently without redundant computation.
   * This results in an overall complexity of O(V + E).
   * @param args
   */

  public static void main(String[] args) {
    ParallelCoursesIIIDFS p = new ParallelCoursesIIIDFS();
    int[][] relations = new int[][]{{1,3}, {2,3}};
    System.out.println(p.minimumTime(3, relations, new int[]{2,3,5}));
  }
}
