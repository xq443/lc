package Snowflake.graph;

import java.util.*;

class GraphColoringBFS {
  private List<String> locations;
  private int[] colors;

  public GraphColoringBFS(List<String> locations) {
    this.locations = locations;
  }

  public boolean canColor(List<int[]> adjacency, int numColors) {
    int n = locations.size();
    List<List<Integer>> adjList = new ArrayList<>();

    // Initialize adjacency list
    for (int i = 0; i < n; i++) {
      adjList.add(new ArrayList<>());
    }

    // Build the graph
    for (int[] edge : adjacency) {
      int u = edge[0];
      int v = edge[1];
      adjList.get(u).add(v);
      adjList.get(v).add(u);
    }

    colors = new int[n];
    Arrays.fill(colors, 0); // 0 means uncolored

    // Try coloring each connected component
    for (int i = 0; i < n; i++) {
      if (colors[i] == 0) { // If not yet colored, apply BFS
        if (!bfsColorGraph(i, adjList, numColors)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean bfsColorGraph(int start, List<List<Integer>> adjList, int numColors) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    colors[start] = 1; // Assign first color

    while (!queue.isEmpty()) {
      int node = queue.poll();
      int currentColor = colors[node];

      for (int neighbor : adjList.get(node)) {
        if (colors[neighbor] == 0) { // If not yet colored
          colors[neighbor] = (currentColor % numColors) + 1; // Assign next color
          queue.offer(neighbor);
        } else if (colors[neighbor] == currentColor) {
          return false; // Conflict detected
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    List<String> locations = Arrays.asList("A", "B", "C", "D");
    GraphColoringBFS g = new GraphColoringBFS(locations);

    List<int[]> adjacency = Arrays.asList(
        new int[]{0, 1},
        new int[]{1, 2},
        new int[]{2, 3},
        new int[]{3, 0}
    );

    int numColors = 3;

    boolean result = g.canColor(adjacency, 3);

    // Output the result
    if (result) {
      System.out.println("Graph can be colored with 3 colors.");
      // Print the colors assigned to each node
      for (int i = 0; i < locations.size(); i++) {
        System.out.println("Location " + locations.get(i) + " has color " + g.colors[i]);
      }
    } else {
      System.out.println("No solution with the given number of colors.");
    }
  }
}
