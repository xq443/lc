package Snowflake.graph;

import java.util.*;

public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int f = edge[0];
            int t = edge[1];
            adj.get(f).add(t);
            adj.get(t).add(f);
        }

        // bfs
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set =  new HashSet<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if(set.contains(curr)) return false;
            set.add(curr);
            for(int next : adj.get(curr)) {
                queue.add(next);
                adj.get(next).remove((Integer)curr);
            }
        }
        return set.size() == n;
    }

    public static void main(String[] args) {
        GraphValidTree g = new GraphValidTree();
        int[][] edges = new int[][]{{0,1},{0,2},{0,3},{1,4}};
        System.out.println(g.validTree(5, edges));
    }
}
