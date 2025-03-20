package Snowflake.graph;

import java.util.ArrayList;
import java.util.List;

public class ParallelCoursesCycleDFS {
  public int minimumTime(int n, int[][] relations, int[] time) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }

    int[] state = new int[n + 1]; // 0: unvisited, 1: visiting, 2: visited
    int[] memo = new int[n + 1];

    for (int[] relation : relations) {
      int prep = relation[0];
      int course = relation[1];
      adj.get(prep).add(course);
    }

    int ret = 0;
    for (int i = 1; i <= n; i++) {
      if (dfs(i, adj, time, memo, state) == -1) {
        return -1; // Cycle detected
      }
      ret = Math.max(ret, memo[i]);
    }
    return ret;
  }

  public int dfs(int i, List<List<Integer>> adj, int[] time, int[] memo, int[] state) {
    if (state[i] == 1) return -1; // Cycle detected
    if (state[i] == 2) return memo[i]; // Already processed

    state[i] = 1; // Mark as visiting
    int max = time[i - 1];

    for (int next : adj.get(i)) {
      int res = dfs(next, adj, time, memo, state);
      if (res == -1) return -1; // Propagate cycle detection
      max = Math.max(max, time[i - 1] + res);
    }

    state[i] = 2; // Mark as fully processed
    memo[i] = max;
    return max;
  }

  public static void main(String[] args) {
    ParallelCoursesCycleDFS p = new ParallelCoursesCycleDFS();
    int[][] relations = {{1,3}, {2,3}, {3,1}}; // Cycle
    int result = p.minimumTime(3, relations, new int[]{2,3,5});
    System.out.println(result == -1 ? "Cycle detected" : result);
  }
}
