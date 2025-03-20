package Snowflake.graph;

import java.util.*;

public class CouseSchduleIICycle {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int pre = prerequisite[1];
            int course = prerequisite[0];
            adj.get(pre).add(course);
        }

        int[] visited = new int[numCourses]; // 0 = unvisited, 1 = visiting, 2 = visited
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0 && hasCycle(i, adj, visited, stack)) {
                return new int[0]; // cycle detected
            }
        }

        int[] order = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            order[i] = stack.pop();
        }
        return order;
    }

    private boolean hasCycle(int node, List<List<Integer>> adj, int[] visited, Stack<Integer> stack) {
        if (visited[node] == 1) return true; // cycle detected
        if (visited[node] == 2) return false; // already processed

        visited[node] = 1;
        for (int neighbor : adj.get(node)) {
            if (hasCycle(neighbor, adj, visited, stack)) {
                return true;
            }
        }
        visited[node] = 2;
        stack.push(node);
        return false;
    }

}
