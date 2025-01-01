package Graph;

import java.util.ArrayList;
import java.util.List;

public class ReorderRoutes {
  public int minReorder(int n, int[][] connections) {
    // build the graph
    List<List<int[]>> graph = new ArrayList<>();
    for(int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for(int[] connection : connections) {
      int u = connection[0];
      int v = connection[1];
      graph.get(u).add(new int[]{v, 0}); // source -> dest not good
      graph.get(v).add(new int[]{u, 1}); // dest -> source good
    }

    boolean[] visited = new boolean[n];
    return reorder_dfs(graph, 0, visited);
  }

  private int reorder_dfs(List<List<int[]>> graph, int node, boolean[] visited) {
    visited[node] = true;
    int ret = 0;
    for(int[] connection : graph.get(node)) {
      int next = connection[0];
      int indicator = connection[1];
      if(!visited[next]) {
        if(indicator == 0) {
          ret++;
        }
        ret += reorder_dfs(graph, next, visited);
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    ReorderRoutes r =  new ReorderRoutes();
    int n = 6;
    int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
    System.out.println(r.minReorder(n, connections));
  }
}
