import java.util.*;

public class GridOrderFilter {
    public int[][] fillOrder(int n, int[][] rowOrder, int[][] colOrder) {
        int[] rows = topoSort(n, rowOrder);
        int[] cols = topoSort(n, colOrder);
        if(rows == null || cols == null) {
            return new int[0][0];
        }
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            grid[rows[i]][cols[i]] = i;
        }
        return grid;
    }

    private int[] topoSort(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            graph.get(from).add(to);
            indegree[to]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int[] pos = new int[n];
        int idx = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            pos[cur] = idx++;
            for (int next : graph.get(cur)) {
                if (--indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return idx == n ? pos : null; // null if cycle exists
    }

    public static void main(String[] args) {
        GridOrderFilter filler = new GridOrderFilter();
        int n = 3;
        int[][] rowOrders = { {1, 2} , {1, 0}, {0, 2}}; // 1 before 2
        int[][] colOrders = { {0, 2} }; // 0 before 2

        int[][] result = filler.fillOrder(n, rowOrders, colOrders);

        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}

