package Snowflake.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphValidTreeDFS {
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n - 1) return false;
        // Build adjacency list: TC: O(E) = O(n - 1)  SC: O(V + E) -> O(n + n  - 1) = O(n)
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        if(hasCycle(0, -1, visited, adj)) return false;
        return visited.size() == n;
    }

    public boolean hasCycle(int node, int parent, Set<Integer> visited, List<List<Integer>> adj) {
        visited.add(node);
        for(int next: adj.get(node)) {
            if(next == parent) continue;
            if(visited.contains(next)) return true;
            if(hasCycle(next, node, visited, adj)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        GraphValidTreeDFS f = new GraphValidTreeDFS();
        int[][] edges = new int[][]{{0,1},{0,2},{0,3},{1,4}};
        System.out.println(f.validTree(5, edges));
    }
    // TC: O(N)
    // SC: O(N)
}
