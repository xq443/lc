package Snowflake.bfs;

import java.util.PriorityQueue;

public class PathWithMinEffort {
    public int minimumEffortPath(int[][] heights) {
        if(heights.length == 0 || heights[0].length == 0) return 0;
        int m = heights.length, n = heights[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b)->(a[2] - b[2])
        );
        pq.offer(new int[]{0, 0, 0});

        int[][] dis = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dis[i][j] = Integer.MAX_VALUE;
            }
        }

        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int x = curr[0], y = curr[1];
            int currentEffort = curr[2];

            if(currentEffort > dis[x][y]) continue;
            dis[x][y] = currentEffort;

            for(int i = 0; i < dirs.length; i++) {
                int newX = x + dirs[i][0], newY = y + dirs[i][1];
                if(newX < 0 || newX >= m || newY < 0 || newY >= n) continue;
                int newEffort = Math.max(Math.abs(heights[newX][newY] - heights[x][y]), currentEffort);
                if(dis[newX][newY] > newEffort) {
                    dis[newX][newY] = newEffort;
                    pq.offer(new int[]{newX, newY, newEffort});
                }
            }
        }

        if(dis[m - 1][n - 1] == Integer.MAX_VALUE) return 0;
        return dis[m - 1][n - 1];
    }

    // TC : O(MN LogMN): The total number of operations is m&times;n, and each operation (insertion or removal) takes O(LogMN)
    // and there are MN iterations
    // SC : O(MN)
}
