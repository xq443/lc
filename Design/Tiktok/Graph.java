package Tiktok;

import java.util.*;

class Graph {
  static class Node {
    int id, time;
    public Node(int id, int time) {
      this.id = id;
      this.time = time;
    }
  }

  public static int findShortestTime(int[][] arr, int n, int start, int end) {
    // Step 1: Create adjacency list representation of the graph
    Map<Integer, List<Node>> graph = new HashMap<>();
    for (int i = 0; i < n; i++) {
      int u = arr[i][0]; // start node
      int v = arr[i][1]; // end node
      int time = arr[i][2]; // time
      graph.putIfAbsent(u, new ArrayList<>());
      graph.get(u).add(new Node(v, time));
    }

    // Step 2: Dijkstra's algorithm
    int[] times = new int[n]; // Stores the shortest time to reach each node
    Arrays.fill(times, Integer.MAX_VALUE);
    times[start] = 0;

    PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));
    pq.add(new Node(start, 0));

    while (!pq.isEmpty()) {
      Node current = pq.poll();
      int currentNode = current.id;
      int currentTime = current.time;

      if (currentNode == end) {
        return currentTime;
      }

      // Step 3: Process neighbors of current node
      if (graph.containsKey(currentNode)) {
        for (Node neighbor : graph.get(currentNode)) {
          int newTime = currentTime + neighbor.time;
          if (newTime < times[neighbor.id]) {
            times[neighbor.id] = newTime;
            pq.add(new Node(neighbor.id, newTime));
          }
        }
      }
    }

    return -1; // If there's no path from start to end
  }

  public static void main(String[] args) {
    int[][] arr = {
        {0, 1, 10},
        {1, 2, 20},
        {0, 2, 50},
        {2, 3, 10},
        {1, 3, 30}
    };

    int n = 4; // Number of nodes
    int start = 0; // Start node
    int end = 3;   // End node

    int shortestTime = findShortestTime(arr, n, start, end);
    System.out.println("Shortest time from node " + start + " to node " + end + ": " + shortestTime);
  }
}
