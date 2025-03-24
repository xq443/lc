package Snowflake.graph;

import java.util.*;

class GraphColoring {
    // Locations (nodes) and colors array
    List<String> locations;
    int[] colors; // 颜色数组，初始化为0表示未着色

    // Constructor to initialize locations
    public GraphColoring(List<String> locations) {
        this.locations = locations;
    }

    // Check if the graph can be colored
    public boolean canColor(List<int[]> adjacency, int numColors) {
        int n = locations.size(); // 节点数
        List<List<Integer>> adjList = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        colors = new int[n]; // Initialize colors array
        Arrays.fill(colors, 0); // Fill the colors array with 0 (no color)

        // Build the graph (adjacency list)
        for (int[] edge : adjacency) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // Try to color the graph with 'numColors' colors
        return canColorGraph(0, adjList, numColors);
    }

    // Backtracking function to color the graph
    public boolean canColorGraph(int node, List<List<Integer>> adjList, int numColors) {
        if (node == adjList.size()) {
            return true; // All nodes have been successfully colored
        }

        for (int color = 1; color <= numColors; color++) {
            if (isValidColor(node, color, adjList)) {
                colors[node] = color; // Assign color to the node
                if (canColorGraph(node + 1, adjList, numColors)) {
                    return true; // Recursively try to color the next node
                }
                colors[node] = 0; // Backtrack if coloring fails
            }
        }
        return false; // Cannot color the current node
    }

    // Helper method to check if the current color assignment is valid
    private boolean isValidColor(int node, int color, List<List<Integer>> adjList) {
        for (int neighbor : adjList.get(node)) {
            if (colors[neighbor] == color) {
                return false; // Neighbor has the same color, invalid
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Define the locations and graph edges
        List<String> locations = Arrays.asList("A", "B", "C", "D");
        List<int[]> adjacencies = Arrays.asList(
                new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 0}, new int[]{3, 1}
        );

        // Create an instance of GraphColoring with the locations
        GraphColoring g = new GraphColoring(locations);

        // Try to color the graph with 3 colors
        boolean result = g.canColor(adjacencies, 3);

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

    // TC: O(E + N) + O(k^N)
    // SC: O(E + N)
    // N: # of nodes E: # of edges
}
