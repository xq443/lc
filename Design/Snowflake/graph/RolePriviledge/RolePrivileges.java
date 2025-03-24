package Snowflake.graph.RolePriviledge;

import java.util.*;

public class RolePrivileges {
    public List<List<String>> getAllowedPrivileges(int n, int[][] grants, List<List<String>> allowedList, List<List<String>> disallowedList) {
        // Step 1: Create graph and in-degree array: TC: O(N + M) M is the length of grants
        List<List<Integer>> next = new ArrayList<>();
        int[] inDegree = new int[n];

        for (int i = 0; i < n; i++) {
            next.add(new ArrayList<>());
        }

        for (int[] grant : grants) {
            int from = grant[1];
            int to = grant[0];
            next.get(to).add(from);
            inDegree[from]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Set<String>> allowRet = new ArrayList<>(n);
        List<Set<String>> disallowRet = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            allowRet.add(new HashSet<>(allowedList.get(i)));
            disallowRet.add(new HashSet<>(disallowedList.get(i)));
        }

        while (!queue.isEmpty()) {
            int role = queue.poll();

            for (int child : next.get(role)) {
                // 先继承父节点的权限
                allowRet.get(child).addAll(allowRet.get(role)); // TC: O(N * K) K is the avg # of privileges
                disallowRet.get(child).addAll(disallowRet.get(role));

                // 再去掉不允许的权限
                allowRet.get(child).removeAll(disallowRet.get(child));

                inDegree[child]--;
                if (inDegree[child] == 0) {
                    queue.offer(child);
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (Set<String> set : allowRet) {
            result.add(new ArrayList<>(set));
        }

        return result;
    }

    public static void main(String[] args) {
        RolePrivileges rp = new RolePrivileges();

        int n = 3;
        int[][] grants = {{0, 1}, {1, 2}};
        List<List<String>> allowedList = Arrays.asList(
            Arrays.asList("A", "B", "D"),
            Arrays.asList("C", "E"),
            Arrays.asList("F")
        );

        List<List<String>> disallowedList = Arrays.asList(
            Arrays.asList("C"),
            Arrays.asList("D"),
            Arrays.asList("E")
        );

        List<List<String>> result = rp.getAllowedPrivileges(n, grants, allowedList, disallowedList);
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Role " + i + ": " + result.get(i));
        }
    }
    // TC: O(N * M + N * K)
    // SC: O(N * M + N * K)
}
