package Snowflake;

import java.util.*;

public class RolePrivilegesDFS {
    public List<List<String>> getAllowedPrivileges(int n, int[][] grants, List<List<String>> allowedList, List<List<String>> disallowedList) throws IllegalAccessException {
        // Step 1: Create graph and in-degree array
        List<List<Integer>> next = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            next.add(new ArrayList<>());
        }

        // Build the graph from the grants list (from role to role)
        for (int[] grant : grants) {
            int from = grant[1];
            int to = grant[0];
            next.get(to).add(from);  // Grant from `from` to `to`
        }

        // Step 2: Initialize privilege sets for each role
        List<Set<String>> allowRet = new ArrayList<>();
        List<Set<String>> disallowRet = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            allowRet.add(new HashSet<>());
            disallowRet.add(new HashSet<>());
        }

        for (int i = 0; i < allowedList.size(); i++) {
            allowRet.get(i).addAll(allowedList.get(i));
        }

        for (int i = 0; i < disallowedList.size(); i++) {
            disallowRet.get(i).addAll(disallowedList.get(i));
        }

        // DFS to calculate the allowed and disallowed privileges
        boolean[] visited = new boolean[n];

        // Start DFS from each role
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, next, allowRet, disallowRet, visited);
            }
        }

        // Convert sets to lists for the result
        List<List<String>> result = new ArrayList<>();
        for (Set<String> set : allowRet) {
            result.add(new ArrayList<>(set));
        }
        return result;
    }

    // DFS Helper method
    private void dfs(int role, List<List<Integer>> next, List<Set<String>> allowRet, List<Set<String>> disallowRet, boolean[] visited) throws IllegalAccessException {
        if (visited[role]) {
            throw new IllegalAccessException("Cycle detected;");
        }
        // Mark the current role as visited and part of the recursion stack
        visited[role] = true;

        // Merge privileges from the parent roles (granted roles)
        for (int child : next.get(role)) {
            // Merge the parent's allowed privileges into the child
            allowRet.get(child).addAll(allowRet.get(role));
            disallowRet.get(child).addAll(disallowRet.get(role));
            allowRet.get(child).removeAll(disallowRet.get(child));

            // Recur for the child role
            dfs(child, next, allowRet, disallowRet, visited);
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        try {
            RolePrivilegesDFS rp = new RolePrivilegesDFS();

            // Sample Input
            int n = 4;
            int[][] grants = {{0, 1}, {1, 2}, {0, 3}, {2, 3}};  // Role 0 grants to Role 1, Role 1 grants to Role 2
            List<List<String>> allowedList = new ArrayList<>();
            allowedList.add(Arrays.asList("A", "B", "D"));  // Role 0 allowed privileges
            allowedList.add(Arrays.asList("C", "E"));      // Role 1 allowed privileges
            allowedList.add(Arrays.asList("F"));           // Role 2 allowed privileges

            List<List<String>> disallowedList = new ArrayList<>();
            disallowedList.add(Arrays.asList("C"));  // Role 0 disallowed privileges
            disallowedList.add(Arrays.asList("D"));  // Role 1 disallowed privileges
            disallowedList.add(Arrays.asList("E"));  // Role 2 disallowed privileges

            List<List<String>> result = rp.getAllowedPrivileges(n, grants, allowedList, disallowedList);
            for (int i = 0; i < result.size(); i++) {
                System.out.println("Role " + i + ": " + result.get(i));
            }
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
// TC : O(V + E)
// SC : O(V + E) + O(V)