package Snowflake.graph.RolePriviledge;

import java.util.*;

public class RolePrivileges {
    public List<List<String>> getAllowedPrivileges(int n, int[][] grants, List<List<String>> allowedList, List<List<String>> disallowedList) {
        // Step 1: Create graph and in-degree array
        List<List<Integer>> next = new ArrayList<>();
        int[] inDegree = new int[n];

        // Initialize graph
        for (int i = 0; i < n; i++) {
            next.add(new ArrayList<>());
        }

        // Build the graph from the grants list (from role to role)
        for (int[] grant : grants) {
            int from = grant[1];
            int to = grant[0];
            next.get(to).add(from);
            inDegree[from]++;
        }

        System.out.println(Arrays.toString(next.toArray()));
        System.out.println(Arrays.toString(inDegree));

        // Step 2: Perform Topological Sort using Kahn's Algorithm (BFS approach)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Set<String>> allowRet = new ArrayList<>();
        List<Set<String>> disallowRet = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            allowRet.add(new HashSet<>());
            disallowRet.add(new HashSet<>());
        }

        for(int i = 0; i < allowedList.size(); i++) {
            allowRet.get(i).addAll(allowedList.get(i));
        }

        for(int i = 0; i < disallowedList.size(); i++) {
            disallowRet.get(i).addAll(disallowedList.get(i));
        }

        // Topological Sort Processing
        while (!queue.isEmpty()) {
            int role = queue.poll();
            // Merge privileges from parent roles (granted roles)
            System.out.println("disallowedPrivileges: " + disallowedList.get(role));
            System.out.println("allowedPrivileges: " + allowedList.get(role));

            for (int children : next.get(role)) {
                allowRet.get(children).addAll(allowRet.get(role));  // Merge the parent's allowed privileges
                disallowRet.get(children).addAll(disallowRet.get(role));
                allowRet.get(children).removeAll(disallowRet.get(children));
            }

            // Process child roles (grantee roles)
            for (int e : next.get(role)) {
                inDegree[e]--;
                if (inDegree[e] == 0) {
                    queue.offer(e);
                }
            }
        }

        List<List<String>> dis = new ArrayList<>();
        List<List<String>> a = new ArrayList<>();
        for (Set<String> set : disallowRet) {
            dis.add(new ArrayList<>(set));
        }
        for (Set<String> set : allowRet) {
            a.add(new ArrayList<>(set));
        }
        return a;
    }

    public static void main(String[] args) {
        RolePrivileges rp = new RolePrivileges();

        // Sample Input
        int n = 3;
        int[][] grants = {{0, 1}, {1, 2}};  // Role 0 grants to Role 1, Role 1 grants to Role 2
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
    }
}
